ti_keychain module
==================

The keychain module provides methods for securely storing sensitive data on iOS and Android.

Installation
------------

1. Download the module ZIP file from the [downloads section](https://github.com/pegli/ti_keychain/downloads)
and save it to either your project's root directory (the same directory as the 
`Resources` folder) or your Titanium SDK installation directory.
1. Edit `tiapp.xml` and add the following line to the `<modules>` section, using the version number 
specified in the ZIP file name:
```
<module version="1.0" platform="android">com.obscure.keychain</module>
```
1. Clean and rebuild your project.

Detailed instructions for installing modules can be found on the
[Appcelerator documentation site](http://docs.appcelerator.com/titanium/latest/#!/guide/Using_a_Module).

Usage Example
-------------

```javascript
var keychain = require('com.obscure.keychain');

// NOTE passphrase is ignored on iOS
var keychainItem = keychain.createKeychainItem('mylogin', 'supersecretpassphrase');

// get values
alert('Your account name is ' + keychainItem.account);

// set values
keychainItem.account = 'pegli'; // username
keychainItem.valueData = 'correctbatteryhorsestaple'; // password
````