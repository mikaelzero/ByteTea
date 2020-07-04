package net.mikaelzero.bytetea.encryptstr;

import com.android.build.gradle.AppExtension;
import com.ss.android.ugc.bytex.common.BaseContext;


import org.gradle.api.Project;

import java.util.ArrayList;
import java.util.List;

public final class Context extends BaseContext<EncryptStringExtension> {

    Context(Project project, AppExtension android, EncryptStringExtension extension) {
        super(project, android, extension);
    }

    private final List<String> ignoreClassNameList = new ArrayList<>();

    @Override
    public void init() {
        super.init();
    }


    void addIgnoreClass(ClassInfo classInfo) {
        ignoreClassNameList.add(classInfo.name);
    }

    public List<String> getIgnoreClassNameList() {
        return ignoreClassNameList;
    }
}
