package net.mikaelzero.app;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.mikaelzero.bytetea.lib.encryptstr.EncryptIgnore;

/**
 * @Author: MikaelZero
 * @CreateDate: 2020/6/30 2:04 PM
 * @Description:
 */
@EncryptIgnore
public class MainActivity extends AppCompatActivity {
    String name = "HELLO WORLD";
    int num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView testTv = findViewById(R.id.testTv);
        testTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testTv.setText(String.valueOf(num));
                num++;
            }
        });
    }
}
