// This is a test harness for your module
// You should do something interesting in this harness 
// to test out the module and to provide instructions 
// to users on how to use it by example.


// open a single window
var window = Ti.UI.createWindow({
	backgroundColor:'white',
});
var label = Ti.UI.createLabel({
  top: 20,
  height: 30,
  left: 10,
  right: 10,
  text: 'keychain test',
});
window.add(label);

var button1 = Ti.UI.createButton({
  title: 'set account',
  top: 60,
  height: 30,
  width: 100,
});
button1.addEventListener('click', function() {
  keychainItem.account = 'pegli';
  Ti.API.info("new account = "+keychainItem.account);
  label.text = "new account = "+keychainItem.account;
});
window.add(button1);

var button2 = Ti.UI.createButton({
  title: 'set password',
  top: 100,
  height: 30,
  width: 100,
});
button2.addEventListener('click', function() {
  keychainItem.valueData = 'supersecret';
  Ti.API.info("new password = "+keychainItem.valueData);
  label.text = "new password = "+keychainItem.valueData;
});
window.add(button2);

var button3 = Ti.UI.createButton({
  title: 'reset keychain item',
  top: 140,
  height: 30,
  width: 100,
});
button3.addEventListener('click', function() {
  keychainItem.reset();
  Ti.API.info("reset item, account = "+keychainItem.account);
  label.text = "reset item, account = "+keychainItem.account;
});
window.add(button3);

window.open();

// TODO: write your module tests here
var keychain = require('com.obscure.keychain');
Ti.API.info("module is => " + keychain);

var keychainItem = keychain.createKeychainItem('mylogin');
Ti.API.info("account = " + keychainItem.account + "; password = " + keychainItem.valueData);