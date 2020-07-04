package net.mikaelzero.bytetea.clickdebounce.visitor;


import net.mikaelzero.bytetea.clickdebounce.ClickDebounceExtension;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TimeMethodVisitor extends MethodVisitor {
    private ClickDebounceExtension clickDebounceExtension;

    public TimeMethodVisitor(MethodVisitor mv, ClickDebounceExtension clickDebounceExtension) {
        super(Opcodes.ASM5, mv);
        this.clickDebounceExtension = clickDebounceExtension;
    }


    @Override
    public void visitLdcInsn(Object value) {
        if (value instanceof Long) {
            if (clickDebounceExtension.getTime() == 0L) {
                super.visitLdcInsn(300L);
            } else {
                super.visitLdcInsn(clickDebounceExtension.getTime());
            }
        } else {
            super.visitLdcInsn(value);
        }
    }
}
