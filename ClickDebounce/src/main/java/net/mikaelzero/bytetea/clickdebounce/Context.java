package net.mikaelzero.bytetea.clickdebounce;

import com.android.build.gradle.AppExtension;
import com.ss.android.ugc.bytex.common.BaseContext;

import org.gradle.api.Project;

public final class Context extends BaseContext<ClickDebounceExtension> {

    Context(Project project, AppExtension android, ClickDebounceExtension extension) {
        super(project, android, extension);
    }

    @Override
    public void init() {
        super.init();

    }
}
