package net.mikaelzero.bytetea.encryptstr

import com.android.build.gradle.AppExtension
import com.ss.android.ugc.bytex.common.BaseContext
import org.apache.commons.codec.binary.Base64
import org.gradle.api.Project
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.FieldNode
import java.util.*

class Context internal constructor(project: Project?, android: AppExtension?, extension: EncryptStringExtension?) : BaseContext<EncryptStringExtension?>(project, android, extension) {
    val ignoreClassNameList: MutableList<String?> = ArrayList()


    fun addIgnoreClass(classInfo: ClassInfo?) {
        ignoreClassNameList.add(classInfo?.name + ".class")
    }
}