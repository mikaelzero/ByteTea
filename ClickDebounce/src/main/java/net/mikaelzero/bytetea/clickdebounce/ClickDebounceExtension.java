package net.mikaelzero.bytetea.clickdebounce;

import com.ss.android.ugc.bytex.common.BaseExtension;

import java.util.Collections;
import java.util.List;

public class ClickDebounceExtension extends BaseExtension {

    private long time;

    private List<String> whitePackage;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<String> getWhitePackage() {
        return whitePackage;
    }

    public void setWhitePackage(List<String> whitePackage) {
        this.whitePackage = whitePackage;
    }

    @Override
    public String getName() {
        return "click_debounce";
    }
}
