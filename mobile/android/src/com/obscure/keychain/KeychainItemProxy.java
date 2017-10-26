package com.obscure.keychain;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiC;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiCompositeLayout;
import org.appcelerator.titanium.view.TiCompositeLayout.LayoutArrangement;
import org.appcelerator.titanium.view.TiUIView;

import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.app.Activity;

// This proxy can be created by calling Keychain.createExample({message: "hello world"})
@Kroll.proxy(creatableInModule = KeychainModule.class)
public class KeychainItemProxy extends KrollProxy {

    private static final String ACCOUNT_KEY     = "keychain.account";

    private static final String COMMENT_KEY     = "keychain.comment";

    private static final String DESCRIPTION_KEY = "keychain.description";

    private static final String LCAT            = "KeychainItemProxy";

    private static final String VALUE_DATA_KEY  = "keychain.valueData";

    private ContextWrapper      androidContext;

    private DesEncryptor        encryptor;

    private TiApplication       appContext;

    private Activity            activity;

    private String              identifier;

    private SharedPreferences   sharedPrefs;

    // Constructor
    public KeychainItemProxy() {
        super();
        appContext = TiApplication.getInstance();
        activity = appContext.getCurrentActivity();
    }

    private String fetchValueForKey(String key) {
        String value = sharedPrefs.getString(key, null);
        if (encryptor != null && value != null) {
            try {
                value = encryptor.decrypt(value);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value != null ? value : "";
    }

    @Kroll.getProperty(name = "account")
    public String getAccount() {
        return fetchValueForKey(ACCOUNT_KEY);
    }

    @Kroll.getProperty(name = "comment")
    public String getComment() {
        return fetchValueForKey(COMMENT_KEY);
    }

    @Kroll.getProperty(name = "description")
    public String getDescription() {
        return fetchValueForKey(DESCRIPTION_KEY);
    }

    @Kroll.getProperty(name = "identifier")
    public String getIdentifier() {
        return identifier;
    }

    @Kroll.getProperty(name = "valueData")
    public String getValueData() {
        return fetchValueForKey(VALUE_DATA_KEY);
    }

    // Handle creation options
	@Override
	public void handleCreationDict(KrollDict options)
	{
        super.handleCreationDict(options);
        Log.d(LCAT, "****** handleCreationDict","started");
        try {
            if (options.containsKey("name")) {
                String name = String.format("keychain.%s",  options.get("name"));
                sharedPrefs = activity.getSharedPreferences(name, 0);
            }
            if (options.containsKey("group")) {
                encryptor = new DesEncryptor((String) options.get("group"));
            }
        }
        catch (Exception e) {
                Log.d(LCAT, "error creating keystore", e);
            }
	}

    @Kroll.method
    public void reset() {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.remove(ACCOUNT_KEY);
        editor.remove(VALUE_DATA_KEY);
        editor.commit();
    }

    @Kroll.setProperty(name = "account")
    public void setAccount(String value) {
        storeValueForKey(ACCOUNT_KEY, value);
    }

    @Kroll.setProperty(name = "comment")
    public void setComment(String value) {
        storeValueForKey(COMMENT_KEY, value);
    }

    @Kroll.setProperty(name = "description")
    public void setDescription(String value) {
        storeValueForKey(DESCRIPTION_KEY, value);
    }

    @Kroll.setProperty(name = "valueData")
    public void setValueData(String value) {
        storeValueForKey(VALUE_DATA_KEY, value);
    }

    private void storeValueForKey(String key, String value) {
        if (encryptor != null) {
            try {
                value = encryptor.encrypt(value);
            }
            catch (Exception e) {
                Log.d(LCAT, "", e);
            }
        }
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(key, value);
        editor.commit();
    }
}