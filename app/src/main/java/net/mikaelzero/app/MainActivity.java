package net.mikaelzero.app;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.mikaelzero.bytetea.lib.clickdebounce.DebouncedWarp;
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

    private void test222(){
        SpannableString spannableStr = new SpannableString("你好");
        spannableStr.setSpan(new Clickable(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
            }
        }), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private class Clickable extends ClickableSpan implements View.OnClickListener {
        private View.OnClickListener mListener;

        public Clickable(View.OnClickListener mListener) {
            this.mListener = mListener;
        }

        //设置显示样式
        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);//设置下划线
            ds.clearShadowLayer();
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }
    }
}
