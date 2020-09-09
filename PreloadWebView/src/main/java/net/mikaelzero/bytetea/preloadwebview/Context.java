package net.mikaelzero.bytetea.preloadwebview;

import com.android.build.gradle.AppExtension;
import com.ss.android.ugc.bytex.common.BaseContext;
import com.ss.android.ugc.bytex.common.BaseExtension;

import org.gradle.api.Project;

public final class Context extends BaseContext<BaseExtension> {

    Context(Project project, AppExtension android, BaseExtension extension) {
        super(project, android, extension);
    }

    @Override
    public void init() {
        super.init();

    }
}
