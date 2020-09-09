package net.mikaelzero.bytetea.encryptstr

import com.ss.android.ugc.bytex.common.visitor.BaseClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import org.objectweb.asm.tree.FieldNode

/**
 * @Author:         MikaelZero
 * @CreateDate:     2020/9/9 1:32 PM
 * @Description:
 */
class EncryptStringClassVisitor : BaseClassVisitor() {
    var owner: String? = null
    val staticFinalStringFieldNodeList = mutableListOf<FieldNode>()

    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<out String>?) {
        super.visit(version, access, name, signature, superName, interfaces)
        owner = name
    }

    override fun visitField(access: Int, name: String?, descriptor: String?, signature: String?, value: Any?): FieldVisitor {
        if (value != null && value is String
            && descriptor == Type.getDescriptor(String::class.java)
            && 0 != (Opcodes.ACC_STATIC and access)
            && 0 != (Opcodes.ACC_FINAL and access)
        ) {
            //添加一个类下的 所有  static final 的字段
            staticFinalStringFieldNodeList.add(FieldNode(access, name, descriptor, signature, value))
            return super.visitField(access, name, descriptor, signature, null)
        }
        return super.visitField(access, name, descriptor, signature, value)
    }

    override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?, exceptions: Array<String?>?): MethodVisitor? {
        var mv = super.visitMethod(access, name, desc, signature, exceptions)
        mv = EncryptStringMethodVisitor(mv, name, owner, staticFinalStringFieldNodeList)
        return mv
    }

}