/**
 * Your Copyright Here
 *
 * Appcelerator Titanium is Copyright (c) 2009-2010 by Appcelerator, Inc.
 * and licensed under the Apache Public License (version 2)
 */
#import <CoreFoundation/CoreFoundation.h>
#import <Security/Security.h>
#import "ComObscureKeychainModule.h"
#import "KeychainItemWrapperProxy.h"
#import "TiBase.h"
#import "TiHost.h"
#import "TiUtils.h"

@implementation ComObscureKeychainModule

#pragma mark Internal

// this is generated for your module, please do not change it
-(id)moduleGUID
{
	return @"acf8434d-5290-4b73-8f39-e4cd7349860a";
}

// this is generated for your module, please do not change it
-(NSString*)moduleId
{
	return @"com.obscure.keychain";
}

#pragma mark Lifecycle

-(void)startup
{
	// this method is called when the module is first loaded
	// you *must* call the superclass
	[super startup];
	
	NSLog(@"[INFO] %@ loaded",self);
}

-(void)shutdown:(id)sender
{
	// this method is called when the module is being unloaded
	// typically this is during shutdown. make sure you don't do too
	// much processing here or the app will be quit forceably
	
	// you *must* call the superclass
	[super shutdown:sender];
}

#pragma mark Cleanup 

-(void)dealloc
{
	// release any resources that have been retained by the module
	[super dealloc];
}

#pragma mark Internal Memory Management

-(void)didReceiveMemoryWarning:(NSNotification*)notification
{
	// optionally release any resources that can be dynamically
	// reloaded once memory is available - such as caches
	[super didReceiveMemoryWarning:notification];
}

#pragma mark Listener Notifications

-(void)_listenerAdded:(NSString *)type count:(int)count
{
	if (count == 1 && [type isEqualToString:@"my_event"])
	{
		// the first (of potentially many) listener is being added 
		// for event named 'my_event'
	}
}

-(void)_listenerRemoved:(NSString *)type count:(int)count
{
	if (count == 0 && [type isEqualToString:@"my_event"])
	{
		// the last listener called for event named 'my_event' has
		// been removed, we can optionally clean up any resources
		// since no body is listening at this point for that event
	}
}

#pragma Public APIs

- (id)createKeychainItem:(id)args {
    NSArray * arr = (NSArray *)args;
    if (!arr || [arr count] < 1) return nil;
    
    NSString * identifier = [TiUtils stringValue:[arr objectAtIndex:0]];
    NSString * accessGroup = nil;
    if ([arr count] > 1) {
        accessGroup = [TiUtils stringValue:[arr objectAtIndex:1]];
    }
    
    return [[[KeychainItemWrapperProxy alloc] initWithIdentifier:identifier accessGroup:accessGroup] autorelease];
}

@end
