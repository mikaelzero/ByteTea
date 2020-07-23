package net.mikaelzero.bytetea.clickdebounce;

import com.android.build.gradle.AppExtension;
import com.ss.android.ugc.bytex.common.CommonPlugin;
import com.ss.android.ugc.bytex.common.visitor.ClassVisitorChain;

import net.mikaelzero.bytetea.clickdebounce.visitor.FindClickClassVisitor;
import net.mikaelzero.bytetea.clickdebounce.visitor.TimeClassVisitor;

import org.gradle.api.Project;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class ClickDebouncePlugin extends CommonPlugin<ClickDebounceExtension, Context> {
    @Override
    protected Context getContext(Project project, AppExtension android, ClickDebounceExtension extension) {
        return new Context(project, android, extension);
    }

    @Override
    public boolean transform(@Nonnull String relativePath, @Nonnull ClassVisitorChain chain) {
        if (relativePath.equals("net/mikaelzero/bytetea/lib/clickdebounce/DebouncedWarp.class")) {
            chain.connect(new TimeClassVisitor(extension));
        } else {
            chain.connect(new FindClickClassVisitor());
        }
        return super.transform(relativePath, chain);
    }

    @Override
    protected void onApply(@Nonnull @NotNull Project project) {
        super.onApply(project);
        project.getDependencies().add("implementation","net.mikaelzero.bytetea:click-debounce-lib:0.0.3");
    }
}