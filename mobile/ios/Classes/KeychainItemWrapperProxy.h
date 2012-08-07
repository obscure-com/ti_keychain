//
//  KeychainItemWrapperProxy.h
//  keychain
//
//  Created by Paul Mietz Egli on 7/20/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "TiProxy.h"
#import "KeychainItemWrapper.h"

@interface KeychainItemWrapperProxy : TiProxy {
    @private
    KCKeychainItemWrapper * keychainItem;
}
@property (nonatomic, retain) NSString * account;
@property (nonatomic, retain) NSString * valueData;
- (id)initWithIdentifier:(NSString *)identifier accessGroup:(NSString *)accessGroup;
@end
