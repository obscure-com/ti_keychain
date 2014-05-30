ti_keychain module
==================

The keychain module provides methods for securely storing sensitive data on iOS and Android.

*IMPORTANT NOTE*

Version 1.0 for iOS contains a [design flaw](https://github.com/pegli/ti_keychain/issues/5)
where the identifier passed to `createKeychainItem()` is stored in the account field of the
keychain item.  If you ever change the value of the `account` property, a new item will be
created.  In version 2.0, this problem has been fixed by assigning the identifier to the
label field of the keychain item.  If you are using 1.0 and want to upgrade to 2.0, you
will need to continue to use the account name as the keychain item identifier, but will be
free to change the `account` property of the keychain item once it is fetched.

Installation
------------

[![gitTio](http://gitt.io/badge.png)](http://gitt.io/component/com.obscure.keychain)

1. Download the module ZIP file from Amazon S3:
    * [iOS version 2.0](https://github.com/pegli/ti_keychain/blob/master/mobile/ios/dist/com.obscure.keychain-iphone-2.0.zip)
    * [Android version 2.0](https://github.com/pegli/ti_keychain/blob/master/mobile/android/dist/com.obscure.keychain-android-2.0.zip)
2. Save the module ZIP to either your project's root directory (the same directory as the 
   `Resources` folder) or your Titanium SDK installation directory.
1. Edit `tiapp.xml` and add the following line to the `<modules>` section, using the version number 
   specified in the ZIP file name:
```
<module version="2.0">com.obscure.keychain</module>
```
1. Clean and rebuild your project.

Detailed instructions for installing modules can be found on the
[Appcelerator documentation site](http://docs.appcelerator.com/titanium/latest/#!/guide/Using_a_Module).

Usage Example
-------------

    var keychain = require('com.obscure.keychain');

    // NOTE passphrase is ignored on iOS
    var keychainItem = keychain.createKeychainItem('server account', 'supersecretpassphrase');

    // get values
    alert('Your account name is ' + keychainItem.account);

    // set values
    keychainItem.account = 'pegli'; // username
    keychainItem.valueData = 'correctbatteryhorsestaple'; // password
