package com.obscure.keychain;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.util.TiConfig;

import android.content.ContextWrapper;
import android.content.SharedPreferences;

// This proxy can be created by calling Keychain.createExample({message: "hello world"})
@Kroll.proxy(creatableInModule = KeychainModule.class)
public class KeychainItemProxy extends KrollProxy {
    // Standard Debugging variables
    private static final String  LCAT           = "KeychainItemProxy";
    private static final boolean DBG            = TiConfig.LOGD;

    private static final String  ACCOUNT_KEY    = "keychain.account";
    private static final String  VALUE_DATA_KEY = "keychain.valueData";

    private SharedPreferences    sharedPrefs;
    private ContextWrapper androidContext;

    // Constructor
    public KeychainItemProxy(TiContext tiContext) {
        super(tiContext);
        androidContext = tiContext.getAndroidContext();
    }

    @Override
    public void handleCreationArgs(KrollModule createdInModule, Object[] args) {
        super.handleCreationArgs(createdInModule, args);
        if (args != null && args.length > 0) {
            String name = String.format("keychain.%s", (String) args[0]);
            sharedPrefs = androidContext.getSharedPreferences(name, 0);
        }
    }

    @Kroll.getProperty
    @Kroll.method
    public String getAccount() {
        return sharedPrefs.getString(ACCOUNT_KEY, null);
    }

    @Kroll.setProperty
    @Kroll.method
    public void setAccount(String value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(ACCOUNT_KEY, value);
        editor.commit();
    }

    @Kroll.getProperty
    @Kroll.method
    public String getValueData() {
        return sharedPrefs.getString(VALUE_DATA_KEY, null);
    }

    @Kroll.setProperty
    @Kroll.method
    public void setValueData(String value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(VALUE_DATA_KEY, value);
        editor.commit();
    }
    
    @Kroll.method
    public void reset() {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.remove(ACCOUNT_KEY);
        editor.remove(VALUE_DATA_KEY);
        editor.commit();
    }
}