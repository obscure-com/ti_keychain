require('ti-mocha');

var should = require('should');

module.exports = function() {
  describe('module', function() {
    var module = require('com.obscure.keychain');
  
    it('must exist', function() {
      should.exist(module);
    });
    
    it('must have a createKeychainItem method', function() {
      should(module.createKeychainItem).be.a.Function;
    });
  });
};