package net.mikaelzero.bytetea.encryptstr

import com.ss.android.ugc.bytex.common.visitor.BaseClassVisitor
import org.objectweb.asm.*
import org.objectweb.asm.tree.FieldNode

/**
 * @Author: MikaelZero
 * @CreateDate: 2020/7/3 4:39 PM
 * @Description:
 */
internal class FindIgnoreClass(private val context: Context) : BaseClassVisitor() {
    private var classInfo: ClassInfo? = null

    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<out String>?) {
        super.visit(version, access, name, signature, superName, interfaces)
        classInfo = ClassInfo(access, name, superName, interfaces)
    }

    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
        if (descriptor == "Lnet/mikaelzero/bytetea/lib/encryptstr/EncryptIgnore;") {
            context.addIgnoreClass(classInfo)
        }
        return super.visitAnnotation(descriptor, visible)
    }
}