package net.mikaelzero.bytetea.encryptstr;

import com.ss.android.ugc.bytex.common.visitor.BaseClassVisitor;

import org.objectweb.asm.AnnotationVisitor;

/**
 * @Author: MikaelZero
 * @CreateDate: 2020/7/3 4:39 PM
 * @Description:
 */
class FindIgnoreClass extends BaseClassVisitor {
    private final Context context;
    private ClassInfo classInfo;

    public FindIgnoreClass(Context context) {
        this.context = context;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.classInfo = new ClassInfo(access, name, superName, interfaces);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (descriptor.equals("Lnet/mikaelzero/bytetea/lib/encryptstr/EncryptIgnore;")){
            context.addIgnoreClass(classInfo);
        }
        return super.visitAnnotation(descriptor, visible);
    }
}
