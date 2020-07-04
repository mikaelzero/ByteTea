package net.mikaelzero.bytetea.encryptstr;

import com.ss.android.ugc.bytex.common.BaseExtension;

import java.util.Collections;
import java.util.List;

public class EncryptStringExtension extends BaseExtension {
    private List<String> encryptPackages;

    public List<String> getEncryptPackages() {
        return encryptPackages;
    }

    public void setEncryptPackages(List<String> encryptPackages) {
        this.encryptPackages = encryptPackages;
    }

    @Override
    public String getName() {
        return "encrypt_string";
    }
}
