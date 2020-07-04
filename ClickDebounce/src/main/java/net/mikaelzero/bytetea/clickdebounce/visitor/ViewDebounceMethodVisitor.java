package net.mikaelzero.bytetea.clickdebounce.visitor;


import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.IFNE;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.RETURN;

public class ViewDebounceMethodVisitor extends MethodVisitor {
    public ViewDebounceMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "net/mikaelzero/bytetea/lib/clickdebounce/DebouncedWarp",
                "shouldDoClick", "(Landroid/view/View;)Z", false);
        Label label = new Label();
        mv.visitJumpInsn(IFNE, label);
        mv.visitInsn(RETURN);
        mv.visitLabel(label);
    }
}
