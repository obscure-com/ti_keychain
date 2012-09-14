# keychain Module

## Description

The keychain module provides methods for securely storing sensitive data on
iOS and Android.  See the [Keychain Services Programming Guide](http://bit.ly/MtQ2DJ)
for background information about the Keychain on iOS.

The reference section follows these conventions:

* Text in `code font` refer to module objects.  For example, keychain is a generic term
  but `keychain` refers to a Keychain object.
* The term "dictionary" is used to distinguish document properties objects from generic
  JavaScript Object types.  Parameters and return values of "dictionary" type are Objects
  with key-value pairs and no functions.
* Object functions are listed with parentheses and properties without.  Constants are
  implemented as read-only properties.
  
## Accessing the Module

To access this module from JavaScript, you would do the following:

	var keychain = require("com.obscure.keychain");


## Keychain module

### Properties

### Functions

**createKeychainItem**(identifier, [accessGroup])

Create or retrieve a keychain item.  If you want the new keychain item to be shared among
multiple applications, provide a value for the `accessGroup` parameter.  This value must
be set in each app's `keychain-access-groups` entitlement.  See the [Keychain Services
Reference](http://bit.ly/MtQ2DJ) for details on access groups.

* identifier (string): the unique identifier of the keychain item to create or fetch.
* accessGroup (string, optional): if provided, sets the access group for the item.

## KeychainItem

A `KeychainItem` object is returned by `createKeychainItem()` and holds the secure information
that was read from or should be written to the keychain.  Changes to the properties are
automatically written to the keychain.

### Properties

**account**

string, read/write.  The account name or username of the item.

**valueData**

string, read/write.  The data associated with the keychain item, e.g. the password or other
secured string.

**description**

string, read/write.  A user-visible string describing this item, e.g. "Google account password".

**comment**

string, read/write.  The user-editable comment for this item.

### Functions

**reset**()

Clear all values and remove the item from the keychain.

## Example

    var keychain = require('com.obscure.keychain');
    var keychainItem = keychain.createKeychainItem('mylogin');
    keychainItem.account = 'paul@example.com';
    keychainItem.valueData = 'correcthorsebatterystaple';
