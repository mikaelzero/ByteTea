package net.mikaelzero.bytetea.clickdebounce;


import org.objectweb.asm.Opcodes;


public class Utils implements Opcodes {

    private Utils() {
        throw new AssertionError("no instance");
    }

    public static boolean isPrivate(int access) {
        return (access & ACC_PRIVATE) != 0;
    }

    public static boolean isPublic(int access) {
        return (access & ACC_PUBLIC) != 0;
    }

    static boolean isStatic(int access) {
        return (access & ACC_STATIC) != 0;
    }

    public static boolean isAbstract(int access) {
        return (access & ACC_ABSTRACT) != 0;
    }

    public static boolean isbridge(int access) {
        return (access & ACC_BRIDGE) != 0;
    }

    public static boolean isSynthetic(int access) {
        return (access & ACC_SYNTHETIC) != 0;
    }

    public static boolean isViewOnclickMethod(int access, String name, String desc) {
        return (Utils.isPublic(access) && !Utils.isStatic(access) && !isAbstract(access))
                && name.equals("onClick") //
                && desc.equals("(Landroid/view/View;)V");
    }

    public static boolean isAdapterViewOnItemOnclickMethod(int access, String name, String desc) {
        return (Utils.isPublic(access) && !Utils.isStatic(access) && !isAbstract(access)) &&
                name.equals("onItemClick") && //
                desc.equals("(Landroid/widget/AdapterView;Landroid/view/View;IJ)V");
    }

}