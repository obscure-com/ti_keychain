var keychain = require('com.obscure.keychain');

// Create or fetch the keychain items that store the username
// and password.
var userKeychainItem = keychain.createKeychainItem('username');
var passKeychainItem = keychain.createKeychainItem('password');


var window = Ti.UI.createWindow({
  backgroundColor: 'white',
  layout: 'vertical'
});

window.add(Ti.UI.createLabel({
  top: 8,
  left: 20,
  text: 'Username'
}));

var username = Ti.UI.createTextField({
  top: 8,
  left: 20,
  right: 20,
  width: Ti.UI.FILL,
  height: 28,
  borderStyle: Ti.UI.INPUT_BORDERSTYLE_ROUNDED,
  value: userKeychainItem.valueData
});
window.add(username);

window.add(Ti.UI.createLabel({
  top: 8,
  left: 20,
  text: 'Password'
}));

var password = Ti.UI.createTextField({
  top: 8,
  left: 20,
  right: 20,
  width: Ti.UI.FILL,
  height: 28,
  borderStyle: Ti.UI.INPUT_BORDERSTYLE_ROUNDED,
  value: passKeychainItem.valueData
});
window.add(password);

var login = Ti.UI.createButton({
  top: 16,
  left: 40,
  right: 40,
  title: 'Login'
});
login.addEventListener('click', function(e) {
  var loginSuccessful = true; // fake the result of a login call
  
  if (loginSuccessful) {
    // store credentials in the keychain
    userKeychainItem.valueData = username.value;
    passKeychainItem.valueData = password.value;
    alert('credentials stored!');
  }
});
window.add(login);

window.open();
