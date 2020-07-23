# ClickDebounce

## feature

handle Android's click debounce, specially a quick double click.

## Usage

```gradle

classpath "net.mikaelzero.bytetea:click-debounce-plugin:$byteTeaVersion"


apply plugin: 'bytex'
apply plugin: 'bytetea.click_debounce'


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