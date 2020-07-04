package net.mikaelzero.app;


import android.util.Log;

public class Test {

    private static final String STATIC_FINAL_FIELD_1 = "test_private_static_final";
    protected static final String STATIC_FINAL_FIELD_2 = "test_protected_static_final";
    public static final String STATIC_FINAL_FIELD_3 = "test_public_static_final";

    private static String static_field_1 = "test_private_static";
    protected static String static_field_2 = "test_protected_static";
    public static String static_field_3 = "test_public_static";

    private final String final_field_1 = "test_private_final";
    protected final String final_field_2 = "test_protected_final";
    public final String final_field_3 = "test_public_final";

    private String normal_field_1 = "test_private_normal";
    protected String normal_field_2 = "test_protected_normal";
    public String normal_field_3 = "test_public_normal";

    public static final String null_static_final;

    public static String null_static;


    static {
        null_static_final = "test_null_static_final";
        null_static = "test_null_static";
        Log.i("stringfog", "test static block");
    }

}
