package net.mikaelzero.bytetea.clickdebounce;

import com.ss.android.ugc.bytex.common.BaseExtension;

import java.util.Collections;
import java.util.List;

public class ClickDebounceExtension extends BaseExtension {

    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String getName() {
        return "click_debounce";
    }
}
