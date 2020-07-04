package net.mikaelzero.bytetea.clickdebounce.visitor;

import com.ss.android.ugc.bytex.common.visitor.BaseClassVisitor;

import net.mikaelzero.bytetea.clickdebounce.ClickDebounceExtension;
import net.mikaelzero.bytetea.clickdebounce.Utils;

import org.objectweb.asm.MethodVisitor;

public class TimeClassVisitor extends BaseClassVisitor {
    private ClickDebounceExtension clickDebounceExtension;

    public TimeClassVisitor(ClickDebounceExtension clickDebounceExtension) {
        this.clickDebounceExtension = clickDebounceExtension;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        mv = new TimeMethodVisitor(mv, clickDebounceExtension);
        return mv;
    }

}
