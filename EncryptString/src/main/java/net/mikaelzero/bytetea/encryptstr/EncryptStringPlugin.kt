package net.mikaelzero.bytetea.encryptstr

import com.android.build.gradle.AppExtension
import com.ss.android.ugc.bytex.common.CommonPlugin
import com.ss.android.ugc.bytex.common.visitor.ClassVisitorChain
import org.apache.commons.codec.binary.Base64
import org.gradle.api.Project
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import org.objectweb.asm.tree.*
import javax.annotation.Nonnull

class EncryptStringPlugin : CommonPlugin<EncryptStringExtension, Context>() {

    val className = "net/mikaelzero/bytetea/lib/encryptstr/Base64Util"
    val methodName = "decode"


    override fun getContext(
        project: Project,
        android: AppExtension,
        extension: EncryptStringExtension
    ): Context {
        return Context(project, android, extension)
    }

    override fun traverse(relativePath: String, chain: ClassVisitorChain) {
        super.traverse(relativePath, chain)
        chain.connect(FindIgnoreClass(context))
    }

    override fun transform(@Nonnull relativePath: String, @Nonnull node: ClassNode): Boolean {

        if (!node.isExcluded) {
            return super.transform(relativePath, node)
        }

        if (context.ignoreClassNameList.contains(node.name)) {
            return super.transform(relativePath, node)
        }

        if (node.name == className || node.name.contains("R$")) {
            return super.transform(relativePath, node)
        }
        var clinitExist = false

        val staticFinalStringFieldNodeList = arrayListOf<FieldNode>()
        node.fields.filter {
            it is FieldNode
                    && it.desc == Type.getDescriptor(String::class.java)
                    && 0 != (Opcodes.ACC_STATIC and it.access)
                    && 0 != (Opcodes.ACC_FINAL and it.access)
                    && it.value != null
        }.forEach {
            staticFinalStringFieldNodeList.add(it)
        }

        node.methods.forEach { method ->
            if (method.name == "<clinit>" && method.desc == "()V") {
                clinitExist = true
            }
            method.instructions.iterator().asIterable().filterIsInstance(LdcInsnNode::class.java)
                .filter {
                    it.cst is String
                }.forEach {
                val str = Base64.encodeBase64String((it.cst as String).toByteArray())
                val il = InsnList()
                il.add(LdcInsnNode(str))
                il.add(
                    MethodInsnNode(
                        Opcodes.INVOKESTATIC,
                        className,
                        methodName,
                        "(Ljava/lang/String;)Ljava/lang/String;"
                    )
                )
                method.instructions.insertBefore(it, il)
                method.instructions.remove(it)
            }
        }

        //如果没有clinit函数(static块)，则需要自己创建clinit函数
        if (!clinitExist && staticFinalStringFieldNodeList.isNotEmpty()) {
            node.createDefaultClinit
        }

        node.methods.filter {
            it.name == "<clinit>" && it.desc == "()V"
        }.forEach { method ->
            method.instructions?.findAll(Opcodes.RETURN, Opcodes.ATHROW)?.forEach {
                staticFinalStringFieldNodeList.forEach { fieldNode ->
                    val str = Base64.encodeBase64String((fieldNode.value as String).toByteArray())
                    val il = InsnList()
                    il.add(LdcInsnNode(str))
                    il.add(
                        MethodInsnNode(
                            Opcodes.INVOKESTATIC,
                            className,
                            methodName,
                            "(Ljava/lang/String;)Ljava/lang/String;"
                        )
                    )
                    il.add(
                        FieldInsnNode(
                            Opcodes.PUTSTATIC,
                            node.name,
                            fieldNode.name,
                            "Ljava/lang/String;"
                        )
                    )
                    // 如果存在clinit函数则需要将代码插入到最前面
                    // 不能出现在static块中 非static变量或常量 之后
                    if (clinitExist) {
                        method.instructions.insert(il)
                    } else {
                        method.instructions.insertBefore(it, il)
                    }
                }
            }
        }

        return super.transform(relativePath, node)
    }

    private val ClassNode.isExcluded: Boolean
        get() = extension.encryptPackages.any {
            this.name.replace("/", ".").startsWith(it)
        }

    private val ClassNode.createDefaultClinit: MethodNode
        get() = MethodNode(Opcodes.ACC_STATIC, "<clinit>", "()V", null, null).apply {
            maxStack = 1
            instructions.insert(InsnNode(Opcodes.RETURN))
            methods?.add(this)
        }

    private fun <T> Iterator<T>.asIterable(): Iterable<T> = Iterable { this }

    private fun InsnList.findAll(vararg opcodes: Int): Collection<AbstractInsnNode> {
        return this.iterator().asIterable().filter {
            it.opcode in opcodes
        }.toList()
    }

    override fun onApply(project: Project) {
        super.onApply(project)
        project.dependencies.add(
            "implementation",
            "net.mikaelzero.bytetea:encrypt-string-lib:0.0.3"
        )
    }
}