package net.mikaelzero.bytetea.preloadwebview

import com.android.build.gradle.AppExtension
import com.ss.android.ugc.bytex.common.BaseExtension
import com.ss.android.ugc.bytex.common.CommonPlugin
import org.gradle.api.Project

class PreloadWebViewPlugin : CommonPlugin<BaseExtension?, Context>() {
    override fun getContext(project: Project?, android: AppExtension?, extension: BaseExtension?): Context {
        return Context(project, android, extension)
    }

}