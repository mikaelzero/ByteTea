# ClickDebounce

## feature

handle Android's click debounce, specially a quick double click.

## Usage

```gradle
allprojects {
    repositories {
        maven { url 'https://dl.bintray.com/mikaelzero/maven'}
    }
}

classpath "com.bytedance.android.byteX:base-plugin:0.1.7"
classpath "net.mikaelzero.bytetea:click-debounce-plugin:0.0.2"


apply plugin: 'bytex'
apply plugin: 'bytetea.click_debounce'

implementation 'net.mikaelzero.bytetea:click-debounce-lib:0.0.1'


click_debounce {
    enable true
    enableInDebug true
    time = 700
}
```


## Results


before:

```java
testTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testTv.setText(String.valueOf(num));
                num++;
            }
        });
```

after:

```java
testTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DebouncedWarp.shouldDoClick(v)){
                    testTv.setText(String.valueOf(num));
                    num++;
                }
            }
        });
```