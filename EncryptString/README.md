# EncryptString

## feature

encrypt string value in your app

## Usage

```gradle
allprojects {
    repositories {
        maven { url 'https://dl.bintray.com/mikaelzero/maven'}
    }
}

classpath "com.bytedance.android.byteX:base-plugin:0.1.6"
classpath "net.mikaelzero.bytetea:encrypt-string-plugin:0.0.1"


implementation 'net.mikaelzero.bytetea:encrypt-string-lib:0.0.1'

encrypt_string {
    enable true
    enableInDebug true
    encryptPackages = [
            "net.mikaelzero.app"
    ]
}
```

### EncryptIgnore

you can use annotation @EncryptIgnore to ignore some class


## Results


before:

```java
private static final String STATIC_FINAL_FIELD_1 = "test_private_static_final";
protected static final String STATIC_FINAL_FIELD_2 = "test_protected_static_final";
public static final String STATIC_FINAL_FIELD_3 = "test_public_static_final";
```

after:

```java
private static final String STATIC_FINAL_FIELD_1 = Base64Util.decode("dGVzdF9wcml2YXRlX3N0YXRpY19maW5hbA==");
protected static final String STATIC_FINAL_FIELD_2 = Base64Util.decode("dGVzdF9wcm90ZWN0ZWRfc3RhdGljX2ZpbmFs");
public static final String STATIC_FINAL_FIELD_3 = Base64Util.decode("dGVzdF9wdWJsaWNfc3RhdGljX2ZpbmFs");
```