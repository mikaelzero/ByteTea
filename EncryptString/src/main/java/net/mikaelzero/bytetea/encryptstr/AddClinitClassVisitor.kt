package net.mikaelzero.bytetea.encryptstr

import com.ss.android.ugc.bytex.common.visitor.BaseClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes


/**
 * @Author:         MikaelZero
 * @CreateDate:     2020/9/9 1:32 PM
 * @Description:
 */
class AddClinitClassVisitor : BaseClassVisitor() {
    var hasClinit = false

    override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?, exceptions: Array<String?>?): MethodVisitor? {
        if (name == "<clinit>") {
            hasClinit = true
        }
        return super.visitMethod(access, name, desc, signature, exceptions)
    }

    override fun visitEnd() {
        if (!hasClinit) {
            val mv = super.visitMethod(Opcodes.ACC_STATIC, "<clinit>", "()V", null, null)
            mv.visitCode()
            mv.visitInsn(Opcodes.RETURN)
            mv.visitMaxs(0, 0)
            mv.visitEnd()
        }
        super.visitEnd()
    }
}