require('ti-mocha');

var should = require('should');

module.exports = function() {
  describe('keychainItem', function() {
    var module = require('com.obscure.keychain');
    
    before(function() {
      var stored_item = module.createKeychainItem('test account');
      stored_item.account = 'paul';
      stored_item.valueData = 'supersecret';
      stored_item.comment = 'a comment';
      stored_item.description = 'my server login';
    });
  
    after(function() {
      module.createKeychainItem('test account').reset();
      module.createKeychainItem('id1').reset();
      module.createKeychainItem('id2').reset();
      module.createKeychainItem('id3').reset();
      module.createKeychainItem('id4').reset();
      module.createKeychainItem('id5').reset();
      module.createKeychainItem('id6').reset();
      module.createKeychainItem('id7').reset();
    });
    
    it('must create a keychainItem', function() {
      var item = module.createKeychainItem('id1');
      should.exist(item);
    });
    
    if (Ti.Platform.osname == 'iphone' || Ti.Platform.osname == 'ipad') {
      it('must create a keychainItem with an accessGroup', function() {
        var item = module.createKeychainItem('id2', 'accessgroup');
        should.exist(item);
      });
    }
    
    if (Ti.Platform.osname == 'android') {
      it('must create a keychainItem with an encryption keyphrase', function() {
        var item = module.createKeychainItem('id2', 'supercalifragalistic');
        should.exist(item);
      });
    }
    
    it('must have an identifier property', function() {
      var item = module.createKeychainItem('id1');
      should(item).have.property('identifier', 'id1');
    });
    
    it('must have an account property', function() {
      var item = module.createKeychainItem('id1');
      should(item).have.property('account', '');
    });
    
    it('must have a valueData property', function() {
      var item = module.createKeychainItem('id1');
      should(item).have.property('valueData', '');
    });
    
    it('must have a description property', function() {
      var item = module.createKeychainItem('id1');
      should(item).have.property('description', '');
    });
    
    it('must have a comment property', function() {
      var item = module.createKeychainItem('id1');
      should(item).have.property('comment', '');
    });
    
    it('must allow changing the account property', function() {
      var item = module.createKeychainItem('id6');
      item.account = 'blather';
      item.valueData = 'oscar';
      
      var r1 = module.createKeychainItem('id6');
      should.exist(r1);
      r1.account.should.eql('blather');
      r1.valueData.should.eql('oscar');
      
      r1.account = 'newuser';
      
      var r2 = module.createKeychainItem('id6');
      should.exist(r2);
      r1.account.should.eql('newuser');
      r1.valueData.should.eql('oscar');
    });
    
    it('must fetch an existing keychain item by identifier', function() {
      // the 'test account' item was created in the before() function
      var item = module.createKeychainItem('test account');
      item.identifier.should.eql('test account');
      item.account.should.eql('paul');
      item.valueData.should.eql('supersecret');
      item.comment.should.eql('a comment');
      item.description.should.eql('my server login');
    });
    
    it('must clear a keychain item', function() {
      var item = module.createKeychainItem('id7');
      item.reset();
      
      var refetch = module.createKeychainItem('id7');
      refetch.account.should.eql('');
      refetch.valueData.should.eql('');
      refetch.comment.should.eql('');
      refetch.description.should.eql('');
    });
  });
};