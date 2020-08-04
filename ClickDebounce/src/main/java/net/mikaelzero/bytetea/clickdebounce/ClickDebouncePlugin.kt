package net.mikaelzero.bytetea.clickdebounce

import com.android.build.gradle.AppExtension
import com.ss.android.ugc.bytex.common.CommonPlugin
import com.ss.android.ugc.bytex.common.visitor.ClassVisitorChain
import net.mikaelzero.bytetea.clickdebounce.visitor.FindClickClassVisitor
import net.mikaelzero.bytetea.clickdebounce.visitor.TimeClassVisitor
import org.gradle.api.Project
import javax.annotation.Nonnull

class ClickDebouncePlugin : CommonPlugin<ClickDebounceExtension?, Context>() {
    override fun getContext(project: Project, android: AppExtension, extension: ClickDebounceExtension?): Context {
        return Context(project, android, extension)
    }

    override fun transform(@Nonnull relativePath: String, @Nonnull chain: ClassVisitorChain): Boolean {
        if (relativePath == "net/mikaelzero/bytetea/lib/clickdebounce/DebouncedWarp.class") {
            chain.connect(TimeClassVisitor(extension))
        } else {
            if (!extension!!.whitePackage.any { relativePath.startsWith(it.replace(".", "/")) }) {
                chain.connect(FindClickClassVisitor())
            }
        }
        return super.transform(relativePath, chain)
    }

    override fun onApply(@Nonnull project: Project) {
        super.onApply(project)
        project.dependencies.add("implementation", "net.mikaelzero.bytetea:click-debounce-lib:0.0.5")
    }
}