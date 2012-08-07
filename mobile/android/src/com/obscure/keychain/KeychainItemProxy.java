package com.obscure.keychain;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiContext;

import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.util.Log;

// This proxy can be created by calling Keychain.createExample({message: "hello world"})
@Kroll.proxy(creatableInModule = KeychainModule.class)
public class KeychainItemProxy extends KrollProxy {

	private static final String	ACCOUNT_KEY		= "keychain.account";

	private static final String	LCAT			= "KeychainItemProxy";

	private static final String	VALUE_DATA_KEY	= "keychain.valueData";

	private ContextWrapper		androidContext;

	private DesEncryptor		encryptor;

	private SharedPreferences	sharedPrefs;

	// Constructor
	public KeychainItemProxy(TiContext tiContext) {
		super(tiContext);
		androidContext = tiContext.getAndroidContext();
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
		return value;
	}

	@Kroll.getProperty
	@Kroll.method
	public String getAccount() {
		return fetchValueForKey(ACCOUNT_KEY);
	}

	@Kroll.getProperty
	@Kroll.method
	public String getValueData() {
		return fetchValueForKey(VALUE_DATA_KEY);
	}

	@Override
	public void handleCreationArgs(KrollModule createdInModule, Object[] args) {
		super.handleCreationArgs(createdInModule, args);
		if (args != null) {

			if (args.length > 0) {
				String name = String.format("keychain.%s", (String) args[0]);
				sharedPrefs = androidContext.getSharedPreferences(name, 0);
			}
			if (args.length > 1) {
				try {
					encryptor = new DesEncryptor((String) args[1]);
				}
				catch (Exception e) {
					Log.d(LCAT, "error creating keystore", e);
				}
			}
		}
	}

	@Kroll.method
	public void reset() {
		SharedPreferences.Editor editor = sharedPrefs.edit();
		editor.remove(ACCOUNT_KEY);
		editor.remove(VALUE_DATA_KEY);
		editor.commit();
	}

	@Kroll.setProperty
	@Kroll.method
	public void setAccount(String value) {
		storeValueForKey(ACCOUNT_KEY, value);
	}

	@Kroll.setProperty
	@Kroll.method
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