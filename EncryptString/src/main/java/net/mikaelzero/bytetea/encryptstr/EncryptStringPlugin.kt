package net.mikaelzero.bytetea.encryptstr

import com.android.build.gradle.AppExtension
import com.ss.android.ugc.bytex.common.CommonPlugin
import com.ss.android.ugc.bytex.common.visitor.BaseClassVisitor
import com.ss.android.ugc.bytex.common.visitor.ClassVisitorChain
import org.apache.commons.codec.binary.Base64
import org.gradle.api.Project
import org.gradle.launcher.daemon.protocol.Build
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.FieldNode

class EncryptStringPlugin : CommonPlugin<EncryptStringExtension, Context>() {

    override fun getContext(project: Project, android: AppExtension, extension: EncryptStringExtension): Context {
        return Context(project, android, extension)
    }

    override fun traverse(relativePath: String, chain: ClassVisitorChain) {
        super.traverse(relativePath, chain)
        chain.connect(FindIgnoreClass(context))
    }

    //relativePath = xxx.class
    override fun transform(relativePath: String, chain: ClassVisitorChain): Boolean {
        if (!relativePath.isExcluded) {
            return super.transform(relativePath, chain)
        }
        if (relativePath.contains("R$")) {
            return super.transform(relativePath, chain)
        }
        if (context.ignoreClassNameList.contains(relativePath)) {
            return super.transform(relativePath, chain)
        }
        chain.connect(AddClinitClassVisitor())
        chain.connect(EncryptStringClassVisitor())
        return super.transform(relativePath, chain)
    }

    override fun onApply(project: Project) {
        super.onApply(project)
        project.dependencies.add("implementation", "net.mikaelzero.bytetea:encrypt-string-lib:1.0")
    }

    private val String.isExcluded: Boolean
        get() = extension.encryptPackages.any {
            this.replace("/", ".").startsWith(it)
        }

}