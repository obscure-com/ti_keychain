//
//  KeychainItemWrapperProxy.m
//  keychain
//
//  Created by Paul Mietz Egli on 7/20/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "KeychainItemWrapperProxy.h"

@implementation KeychainItemWrapperProxy

- (id)initWithIdentifier:(NSString *)identifier accessGroup:(NSString *)accessGroup
{
    self = [super init];
    if (self) {
        keychainItem = [[KCKeychainItemWrapper alloc] initWithIdentifier:identifier accessGroup:accessGroup];
    }
    
    return self;
}

-(void) dealloc {
    [keychainItem release];
    [super dealloc];
}

- (NSString *)identifier {
    return [keychainItem objectForKey:(id)kSecAttrGeneric];
}

- (NSString *)accessGroup {
    return [keychainItem objectForKey:(id)kSecAttrAccessGroup];
}

- (NSString *)account {
    return [keychainItem objectForKey:(id)kSecAttrAccount];
}

- (void)setAccount:(NSString *)value {
    [keychainItem setObject:value forKey:(id)kSecAttrAccount];
}

- (NSString *)valueData {
    return [keychainItem objectForKey:(id)kSecValueData];
}

- (void)setValueData:(NSString *)value {
    [keychainItem setObject:value forKey:(id)kSecValueData];
}

- (NSString *)description {
    return [keychainItem objectForKey:(id)kSecAttrDescription];
}

- (void)setDescription:(NSString *)value {
    [keychainItem setObject:value forKey:(id)kSecAttrDescription];
}

- (NSString *)comment {
    return [keychainItem objectForKey:(id)kSecAttrComment];
}

- (void)setComment:(NSString *)value {
    [keychainItem setObject:value forKey:(id)kSecAttrComment];
}


- (void)reset:(id)args {
    [keychainItem resetKeychainItem];
}

@end
