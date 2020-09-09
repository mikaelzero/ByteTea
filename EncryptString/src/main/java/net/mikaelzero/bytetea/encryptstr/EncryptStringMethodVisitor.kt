package net.mikaelzero.bytetea.encryptstr

import org.apache.commons.codec.binary.Base64
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.FieldNode

/**
 * @Author:         MikaelZero
 * @CreateDate:     2020/9/9 1:32 PM
 * @Description:
 */
class EncryptStringMethodVisitor(
    mv: MethodVisitor?, val methodName: String?,
    val owner: String?,
    val staticFinalStringFieldNodeList: List<FieldNode>
) : MethodVisitor(Opcodes.ASM5, mv) {

    private val className = "net/mikaelzero/bytetea/lib/encryptstr/Base64Util"
    private val encryptMethodName = "decode"


    override fun visitLdcInsn(value: Any?) {
        if (value is String) {
            val str = Base64.encodeBase64String(value.toByteArray())
            mv.visitLdcInsn(str)
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, className, encryptMethodName, "(Ljava/lang/String;)Ljava/lang/String;", false)
            return
        }
        super.visitLdcInsn(value)
    }

    override fun visitInsn(opcode: Int) {
        if (opcode == Opcodes.RETURN && methodName == "<clinit>") {
            staticFinalStringFieldNodeList.forEach { fieldNode ->
                val str = Base64.encodeBase64String((fieldNode.value as String).toByteArray())
                mv?.visitLdcInsn(str)
                mv?.visitMethodInsn(Opcodes.INVOKESTATIC, className, encryptMethodName, "(Ljava/lang/String;)Ljava/lang/String;", false)
                mv?.visitFieldInsn(Opcodes.PUTSTATIC, owner, fieldNode.name, "Ljava/lang/String;")
            }
        }
        super.visitInsn(opcode)
    }


}