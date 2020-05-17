#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class MCCConfigurationManager, MCCBaseMultiConfig, MCCConfiguration, MCCConfigurationBuilder, MCCUiControlsModel, MCCItemBuilder, MCCChoiceBuilder, MCCEditableBuilder, MCCRangeBuilder, MCCSwitchBuilder, MCCItem, MCCKotlinPair, MCCStoreValue, MCCConfigurationRepository, MCCImmutableConfigurationRepository, MCCConfigurationGetterImplementation, MCCStoreValueBooleanValue, MCCStoreValueIntValue, MCCStoreValueKeyValue, MCCStoreValueStringValue, MCCUiControlsModelChoice, MCCUiControlsModelEditable, MCCUiControlsModelRange, MCCUiControlsModelSwitch, MCCAppConfigurationBuilder;

@protocol MCCLaunchController, MCCMultiConfigViewController, MCCConfigurationGetter, MCCConfigBase, MCCSettings, MCCSettingsFactory, MCCBuilder;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wnullability"

__attribute__((swift_name("KotlinBase")))
@interface MCCBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end;

@interface MCCBase (MCCBaseCopying) <NSCopying>
@end;

__attribute__((swift_name("KotlinMutableSet")))
@interface MCCMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end;

__attribute__((swift_name("KotlinMutableDictionary")))
@interface MCCMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end;

@interface NSError (NSErrorMCCKotlinException)
@property (readonly) id _Nullable kotlinException;
@end;

__attribute__((swift_name("KotlinNumber")))
@interface MCCNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end;

__attribute__((swift_name("KotlinByte")))
@interface MCCByte : MCCNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end;

__attribute__((swift_name("KotlinUByte")))
@interface MCCUByte : MCCNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end;

__attribute__((swift_name("KotlinShort")))
@interface MCCShort : MCCNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end;

__attribute__((swift_name("KotlinUShort")))
@interface MCCUShort : MCCNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end;

__attribute__((swift_name("KotlinInt")))
@interface MCCInt : MCCNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end;

__attribute__((swift_name("KotlinUInt")))
@interface MCCUInt : MCCNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end;

__attribute__((swift_name("KotlinLong")))
@interface MCCLong : MCCNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end;

__attribute__((swift_name("KotlinULong")))
@interface MCCULong : MCCNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end;

__attribute__((swift_name("KotlinFloat")))
@interface MCCFloat : MCCNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end;

__attribute__((swift_name("KotlinDouble")))
@interface MCCDouble : MCCNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end;

__attribute__((swift_name("KotlinBoolean")))
@interface MCCBoolean : MCCNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end;

__attribute__((swift_name("LaunchController")))
@protocol MCCLaunchController
@required
- (id _Nullable)launchControllerEnvironment:(NSString *)environment __attribute__((swift_name("launchController(environment:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AppleLaunchController")))
@interface MCCAppleLaunchController : MCCBase <MCCLaunchController>
- (instancetype)initWithController:(id<MCCMultiConfigViewController>)controller __attribute__((swift_name("init(controller:)"))) __attribute__((objc_designated_initializer));
- (id<MCCMultiConfigViewController>)launchControllerEnvironment:(NSString *)environment __attribute__((swift_name("launchController(environment:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ApplicationLaunchController")))
@interface MCCApplicationLaunchController : MCCBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (id<MCCLaunchController>)setLaunchIntentController:(id<MCCMultiConfigViewController>)controller __attribute__((swift_name("setLaunchIntent(controller:)")));
@end;

__attribute__((swift_name("ConfigBase")))
@protocol MCCConfigBase
@required
- (id<MCCConfigurationGetter>)getConfig __attribute__((swift_name("getConfig()")));
- (MCCConfigurationManager *)getConfigurationManager __attribute__((swift_name("getConfigurationManager()")));
- (NSString *)getEnvironment __attribute__((swift_name("getEnvironment()")));
- (id<MCCLaunchController>)getLaunchController __attribute__((swift_name("getLaunchController()")));
- (void)setEnvironmentEnvironment:(NSString *)environment __attribute__((swift_name("setEnvironment(environment:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BaseMultiConfig")))
@interface MCCBaseMultiConfig : MCCBase <MCCConfigBase>
- (instancetype)initWithSettings:(id<MCCSettings> _Nullable)settings startController:(id<MCCLaunchController> _Nullable)startController __attribute__((swift_name("init(settings:startController:)"))) __attribute__((objc_designated_initializer));
- (id<MCCConfigurationGetter>)getConfig __attribute__((swift_name("getConfig()")));
- (MCCConfigurationManager *)getConfigurationManager __attribute__((swift_name("getConfigurationManager()")));
- (NSString *)getEnvironment __attribute__((swift_name("getEnvironment()")));
- (id<MCCLaunchController>)getLaunchController __attribute__((swift_name("getLaunchController()")));
- (void)invokeBody:(void (^)(MCCBaseMultiConfig *))body __attribute__((swift_name("invoke(body:)")));
- (void)multiConfigConfiguration:(NSMutableArray<MCCConfiguration *> *)configuration __attribute__((swift_name("multiConfig(configuration:)")));
- (void)setEnvironmentEnvironment:(NSString *)environment __attribute__((swift_name("setEnvironment(environment:)")));
@end;

__attribute__((swift_name("MultiConfigViewController")))
@protocol MCCMultiConfigViewController
@required
- (void)setEnvironmentEnvironment:(NSString *)environment __attribute__((swift_name("setEnvironment(environment:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MultiConfigure")))
@interface MCCMultiConfigure : MCCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)multiConfigure __attribute__((swift_name("init()")));
- (id<MCCConfigurationGetter>)getConfig __attribute__((swift_name("getConfig()")));
- (MCCConfigurationManager *)getConfigurationManager __attribute__((swift_name("getConfigurationManager()")));
- (NSString *)getEnvironment __attribute__((swift_name("getEnvironment()")));
- (id<MCCLaunchController>)getLaunchController __attribute__((swift_name("getLaunchController()")));
- (void)invokeConfigBase:(id<MCCConfigBase>)configBase __attribute__((swift_name("invoke(configBase:)")));
- (void)invokeEnvironment:(NSString *)environment __attribute__((swift_name("invoke(environment:)")));
@end;

__attribute__((swift_name("Settings")))
@protocol MCCSettings
@required
- (BOOL)getBooleanKey:(NSString *)key defaultValue:(BOOL)defaultValue __attribute__((swift_name("getBoolean(key:defaultValue:)")));
- (int32_t)getIntKey:(NSString *)key defaultValue:(int32_t)defaultValue __attribute__((swift_name("getInt(key:defaultValue:)")));
- (NSString *)getStringKey:(NSString *)key defaultValue:(NSString *)defaultValue __attribute__((swift_name("getString(key:defaultValue:)")));
- (BOOL)hasKeyKey:(NSString *)key __attribute__((swift_name("hasKey(key:)")));
- (void)putBooleanKey:(NSString *)key value:(BOOL)value __attribute__((swift_name("putBoolean(key:value:)")));
- (void)putIntKey:(NSString *)key value:(int32_t)value __attribute__((swift_name("putInt(key:value:)")));
- (void)putStringKey:(NSString *)key value:(NSString *)value __attribute__((swift_name("putString(key:value:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AppleSettings")))
@interface MCCAppleSettings : MCCBase <MCCSettings>
- (instancetype)initWithGroup:(NSString *)group __attribute__((swift_name("init(group:)"))) __attribute__((objc_designated_initializer));
- (BOOL)getBooleanKey:(NSString *)key defaultValue:(BOOL)defaultValue __attribute__((swift_name("getBoolean(key:defaultValue:)")));
- (int32_t)getIntKey:(NSString *)key defaultValue:(int32_t)defaultValue __attribute__((swift_name("getInt(key:defaultValue:)")));
- (NSString *)getStringKey:(NSString *)key defaultValue:(NSString *)defaultValue __attribute__((swift_name("getString(key:defaultValue:)")));
- (BOOL)hasKeyKey:(NSString *)key __attribute__((swift_name("hasKey(key:)")));
- (void)putBooleanKey:(NSString *)key value:(BOOL)value __attribute__((swift_name("putBoolean(key:value:)")));
- (void)putIntKey:(NSString *)key value:(int32_t)value __attribute__((swift_name("putInt(key:value:)")));
- (void)putStringKey:(NSString *)key value:(NSString *)value __attribute__((swift_name("putString(key:value:)")));
@end;

__attribute__((swift_name("SettingsFactory")))
@protocol MCCSettingsFactory
@required
- (id<MCCSettings>)create __attribute__((swift_name("create()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AppleSettings.Factory")))
@interface MCCAppleSettingsFactory : MCCBase <MCCSettingsFactory>
- (instancetype)initWithGroup:(NSString *)group __attribute__((swift_name("init(group:)"))) __attribute__((objc_designated_initializer));
- (id<MCCSettings>)create __attribute__((swift_name("create()")));
@property (readonly) NSString *group __attribute__((swift_name("group")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserSettings")))
@interface MCCUserSettings : MCCBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (id<MCCSettings>)userSettingsGroup:(NSString *)group __attribute__((swift_name("userSettings(group:)")));
@end;

__attribute__((swift_name("Builder")))
@protocol MCCBuilder
@required
- (id _Nullable)build __attribute__((swift_name("build()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AppConfigurationBuilder")))
@interface MCCAppConfigurationBuilder : MCCBase <MCCBuilder>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSMutableArray<MCCConfiguration *> *)build __attribute__((swift_name("build()")));
- (void)configEnvironment:(NSString *)environment configurationBuilder:(void (^)(MCCConfigurationBuilder *))configurationBuilder __attribute__((swift_name("config(environment:configurationBuilder:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChoiceBuilder")))
@interface MCCChoiceBuilder : MCCBase <MCCBuilder>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (MCCUiControlsModel *)build __attribute__((swift_name("build()")));
- (void)itemItemBuilder:(void (^)(MCCItemBuilder *))itemBuilder __attribute__((swift_name("item(itemBuilder:)")));
@property int32_t currentChoiceIndex __attribute__((swift_name("currentChoiceIndex")));
@property (getter=description_) NSString *description __attribute__((swift_name("description")));
@property NSString *key __attribute__((swift_name("key")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Configuration")))
@interface MCCConfiguration : MCCBase
- (instancetype)initWithConfigs:(NSMutableArray<MCCUiControlsModel *> *)configs environment:(NSString *)environment __attribute__((swift_name("init(configs:environment:)"))) __attribute__((objc_designated_initializer));
- (NSMutableArray<MCCUiControlsModel *> *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (MCCConfiguration *)doCopyConfigs:(NSMutableArray<MCCUiControlsModel *> *)configs environment:(NSString *)environment __attribute__((swift_name("doCopy(configs:environment:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSMutableArray<MCCUiControlsModel *> *configs __attribute__((swift_name("configs")));
@property (readonly) NSString *environment __attribute__((swift_name("environment")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConfigurationBuilder")))
@interface MCCConfigurationBuilder : MCCBase <MCCBuilder>
- (instancetype)initWithEnvironment:(NSString *)environment __attribute__((swift_name("init(environment:)"))) __attribute__((objc_designated_initializer));
- (MCCConfiguration *)build __attribute__((swift_name("build()")));
- (void)choiceChoiceBuilder:(void (^)(MCCChoiceBuilder *))choiceBuilder __attribute__((swift_name("choice(choiceBuilder:)")));
- (void)editableEditableBuilder:(void (^)(MCCEditableBuilder *))editableBuilder __attribute__((swift_name("editable(editableBuilder:)")));
- (void)rangeRangeBuilder:(void (^)(MCCRangeBuilder *))rangeBuilder __attribute__((swift_name("range(rangeBuilder:)")));
- (void)switchSwitchBuilder:(void (^)(MCCSwitchBuilder *))switchBuilder __attribute__((swift_name("switch(switchBuilder:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("EditableBuilder")))
@interface MCCEditableBuilder : MCCBase <MCCBuilder>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (MCCUiControlsModel *)build __attribute__((swift_name("build()")));
@property NSString *currentValue __attribute__((swift_name("currentValue")));
@property (getter=description_) NSString *description __attribute__((swift_name("description")));
@property NSString *key __attribute__((swift_name("key")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ItemBuilder")))
@interface MCCItemBuilder : MCCBase <MCCBuilder>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (MCCItem *)build __attribute__((swift_name("build()")));
@property (getter=description_) NSString *description __attribute__((swift_name("description")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RangeBuilder")))
@interface MCCRangeBuilder : MCCBase <MCCBuilder>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (MCCUiControlsModel *)build __attribute__((swift_name("build()")));
@property int32_t currentValue __attribute__((swift_name("currentValue")));
@property (getter=description_) NSString *description __attribute__((swift_name("description")));
@property NSString *key __attribute__((swift_name("key")));
@property int32_t max __attribute__((swift_name("max")));
@property int32_t min __attribute__((swift_name("min")));
@property int32_t step __attribute__((swift_name("step")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SwitchBuilder")))
@interface MCCSwitchBuilder : MCCBase <MCCBuilder>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (MCCUiControlsModel *)build __attribute__((swift_name("build()")));
@property (getter=description_) NSString *description __attribute__((swift_name("description")));
@property NSString *key __attribute__((swift_name("key")));
@property BOOL switchValue __attribute__((swift_name("switchValue")));
@end;

__attribute__((swift_name("ConfigurationGetter")))
@protocol MCCConfigurationGetter
@required
- (BOOL)getConfigBooleanUserKey:(NSString *)userKey __attribute__((swift_name("getConfigBoolean(userKey:)")));
- (int32_t)getConfigIntUserKey:(NSString *)userKey __attribute__((swift_name("getConfigInt(userKey:)")));
- (MCCKotlinPair *)getConfigPairUserKey:(NSString *)userKey __attribute__((swift_name("getConfigPair(userKey:)")));
- (NSString *)getConfigStringUserKey:(NSString *)userKey __attribute__((swift_name("getConfigString(userKey:)")));
@end;

__attribute__((swift_name("ConfigurationGetterImplementation")))
@interface MCCConfigurationGetterImplementation : MCCBase <MCCConfigurationGetter>
- (instancetype)initWithConfigs:(NSMutableArray<MCCUiControlsModel *> *)configs settings:(id<MCCSettings> _Nullable)settings environment:(NSString *)environment __attribute__((swift_name("init(configs:settings:environment:)"))) __attribute__((objc_designated_initializer));
- (BOOL)getConfigBooleanUserKey:(NSString *)userKey __attribute__((swift_name("getConfigBoolean(userKey:)")));
- (int32_t)getConfigIntUserKey:(NSString *)userKey __attribute__((swift_name("getConfigInt(userKey:)")));
- (MCCKotlinPair *)getConfigPairUserKey:(NSString *)userKey __attribute__((swift_name("getConfigPair(userKey:)")));
- (NSString *)getConfigStringUserKey:(NSString *)userKey __attribute__((swift_name("getConfigString(userKey:)")));
- (NSArray<MCCUiControlsModel *> *)loadConfigurationConfigs:(NSMutableArray<MCCUiControlsModel *> *)configs settings:(id<MCCSettings> _Nullable)settings environment:(NSString *)environment __attribute__((swift_name("loadConfiguration(configs:settings:environment:)")));
@property (readonly) MCCMutableDictionary<NSString *, MCCStoreValue *> *store __attribute__((swift_name("store")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConfigurationGetterImplementation.Companion")))
@interface MCCConfigurationGetterImplementationCompanion : MCCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (readonly) NSString *PAIR_SUFFIX_INT __attribute__((swift_name("PAIR_SUFFIX_INT")));
@property (readonly) NSString *PAIR_SUFFIX_STRING __attribute__((swift_name("PAIR_SUFFIX_STRING")));
@property (readonly) NSString *PREFIX __attribute__((swift_name("PREFIX")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConfigurationManager")))
@interface MCCConfigurationManager : MCCBase
- (instancetype)initWithRepository:(NSMutableArray<MCCConfiguration *> *)repository settings:(id<MCCSettings>)settings __attribute__((swift_name("init(repository:settings:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithRepository:(NSMutableArray<MCCConfiguration *> *)repository __attribute__((swift_name("init(repository:)"))) __attribute__((objc_designated_initializer));
- (NSMutableArray<MCCConfiguration *> *)getApplicationConfiguration __attribute__((swift_name("getApplicationConfiguration()")));
- (int32_t)getConfig __attribute__((swift_name("getConfig()")));
- (MCCConfigurationRepository *)getConfigurationEnvironment:(NSString *)environment __attribute__((swift_name("getConfiguration(environment:)")));
- (MCCImmutableConfigurationRepository *)getImmutableConfigurationEnvironment:(NSString *)environment __attribute__((swift_name("getImmutableConfiguration(environment:)")));
- (BOOL)isSettingsInitialized __attribute__((swift_name("isSettingsInitialized()")));
- (void)saveConfigValue:(int32_t)value __attribute__((swift_name("saveConfig(value:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConfigurationManager.Companion")))
@interface MCCConfigurationManagerCompanion : MCCBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (readonly) NSString *KEY __attribute__((swift_name("KEY")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConfigurationRepository")))
@interface MCCConfigurationRepository : MCCConfigurationGetterImplementation
- (instancetype)initWithConfigs:(NSMutableArray<MCCUiControlsModel *> *)configs settings:(id<MCCSettings>)settings environment:(NSString *)environment __attribute__((swift_name("init(configs:settings:environment:)"))) __attribute__((objc_designated_initializer));
- (NSArray<MCCUiControlsModel *> *)getEnvironmentConfiguration __attribute__((swift_name("getEnvironmentConfiguration()")));
- (NSArray<MCCUiControlsModel *> *)loadConfigurationConfigs:(NSMutableArray<MCCUiControlsModel *> *)configs settings:(id<MCCSettings> _Nullable)settings environment:(NSString *)environment __attribute__((swift_name("loadConfiguration(configs:settings:environment:)")));
- (void)saveConfigKey:(NSString *)key value:(BOOL)value __attribute__((swift_name("saveConfig(key:value:)")));
- (void)saveConfigKey:(NSString *)key value_:(int32_t)value __attribute__((swift_name("saveConfig(key:value_:)")));
- (void)saveConfigKey:(NSString *)key value__:(MCCKotlinPair *)value __attribute__((swift_name("saveConfig(key:value__:)")));
- (void)saveConfigKey:(NSString *)key value___:(NSString *)value __attribute__((swift_name("saveConfig(key:value___:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ImmutableConfigurationRepository")))
@interface MCCImmutableConfigurationRepository : MCCConfigurationGetterImplementation
- (instancetype)initWithConfigs:(NSMutableArray<MCCUiControlsModel *> *)configs environment:(NSString *)environment __attribute__((swift_name("init(configs:environment:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithConfigs:(NSMutableArray<MCCUiControlsModel *> *)configs settings:(id<MCCSettings> _Nullable)settings environment:(NSString *)environment __attribute__((swift_name("init(configs:settings:environment:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (NSArray<MCCUiControlsModel *> *)getEnvironmentConfiguration __attribute__((swift_name("getEnvironmentConfiguration()")));
- (NSArray<MCCUiControlsModel *> *)loadConfigurationConfigs:(NSMutableArray<MCCUiControlsModel *> *)configs settings:(id<MCCSettings> _Nullable)settings environment:(NSString *)environment __attribute__((swift_name("loadConfiguration(configs:settings:environment:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Item")))
@interface MCCItem : MCCBase
- (instancetype)initWithInformation:(NSString *)information __attribute__((swift_name("init(information:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MCCItem *)doCopyInformation:(NSString *)information __attribute__((swift_name("doCopy(information:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *information __attribute__((swift_name("information")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Projection")))
@interface MCCProjection : MCCBase
- (instancetype)initWithUserMax:(int32_t)userMax userMin:(int32_t)userMin progressMax:(int32_t)progressMax progressMin:(int32_t)progressMin __attribute__((swift_name("init(userMax:userMin:progressMax:progressMin:)"))) __attribute__((objc_designated_initializer));
@property int32_t progressValue __attribute__((swift_name("progressValue")));
@property int32_t userValue __attribute__((swift_name("userValue")));
@end;

__attribute__((swift_name("StoreValue")))
@interface MCCStoreValue : MCCBase
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StoreValue.BooleanValue")))
@interface MCCStoreValueBooleanValue : MCCStoreValue
- (instancetype)initWithValue:(BOOL)value __attribute__((swift_name("init(value:)"))) __attribute__((objc_designated_initializer));
- (BOOL)component1 __attribute__((swift_name("component1()")));
- (MCCStoreValueBooleanValue *)doCopyValue:(BOOL)value __attribute__((swift_name("doCopy(value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL value __attribute__((swift_name("value")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StoreValue.IntValue")))
@interface MCCStoreValueIntValue : MCCStoreValue
- (instancetype)initWithValue:(int32_t)value __attribute__((swift_name("init(value:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (MCCStoreValueIntValue *)doCopyValue:(int32_t)value __attribute__((swift_name("doCopy(value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t value __attribute__((swift_name("value")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StoreValue.KeyValue")))
@interface MCCStoreValueKeyValue : MCCStoreValue
- (instancetype)initWithKey:(NSString *)key value:(int32_t)value __attribute__((swift_name("init(key:value:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (MCCStoreValueKeyValue *)doCopyKey:(NSString *)key value:(int32_t)value __attribute__((swift_name("doCopy(key:value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *key __attribute__((swift_name("key")));
@property (readonly) int32_t value __attribute__((swift_name("value")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StoreValue.StringValue")))
@interface MCCStoreValueStringValue : MCCStoreValue
- (instancetype)initWithValue:(NSString *)value __attribute__((swift_name("init(value:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (MCCStoreValueStringValue *)doCopyValue:(NSString *)value __attribute__((swift_name("doCopy(value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end;

__attribute__((swift_name("UiControlsModel")))
@interface MCCUiControlsModel : MCCBase
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UiControlsModel.Choice")))
@interface MCCUiControlsModelChoice : MCCUiControlsModel
- (instancetype)initWithKey:(NSString *)key information:(NSString *)information items:(NSMutableArray<MCCItem *> *)items currentChoiceIndex:(int32_t)currentChoiceIndex __attribute__((swift_name("init(key:information:items:currentChoiceIndex:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSMutableArray<MCCItem *> *)component3 __attribute__((swift_name("component3()")));
- (int32_t)component4 __attribute__((swift_name("component4()")));
- (MCCUiControlsModelChoice *)doCopyKey:(NSString *)key information:(NSString *)information items:(NSMutableArray<MCCItem *> *)items currentChoiceIndex:(int32_t)currentChoiceIndex __attribute__((swift_name("doCopy(key:information:items:currentChoiceIndex:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property int32_t currentChoiceIndex __attribute__((swift_name("currentChoiceIndex")));
@property (readonly) NSString *information __attribute__((swift_name("information")));
@property (readonly) NSMutableArray<MCCItem *> *items __attribute__((swift_name("items")));
@property (readonly) NSString *key __attribute__((swift_name("key")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UiControlsModel.Editable")))
@interface MCCUiControlsModelEditable : MCCUiControlsModel
- (instancetype)initWithKey:(NSString *)key information:(NSString *)information currentValue:(NSString *)currentValue __attribute__((swift_name("init(key:information:currentValue:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (MCCUiControlsModelEditable *)doCopyKey:(NSString *)key information:(NSString *)information currentValue:(NSString *)currentValue __attribute__((swift_name("doCopy(key:information:currentValue:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property NSString *currentValue __attribute__((swift_name("currentValue")));
@property (readonly) NSString *information __attribute__((swift_name("information")));
@property (readonly) NSString *key __attribute__((swift_name("key")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UiControlsModel.Range")))
@interface MCCUiControlsModelRange : MCCUiControlsModel
- (instancetype)initWithKey:(NSString *)key information:(NSString *)information min:(int32_t)min max:(int32_t)max currentValue:(int32_t)currentValue __attribute__((swift_name("init(key:information:min:max:currentValue:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (int32_t)component3 __attribute__((swift_name("component3()")));
- (int32_t)component4 __attribute__((swift_name("component4()")));
- (int32_t)component5 __attribute__((swift_name("component5()")));
- (MCCUiControlsModelRange *)doCopyKey:(NSString *)key information:(NSString *)information min:(int32_t)min max:(int32_t)max currentValue:(int32_t)currentValue __attribute__((swift_name("doCopy(key:information:min:max:currentValue:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property int32_t currentValue __attribute__((swift_name("currentValue")));
@property (readonly) NSString *information __attribute__((swift_name("information")));
@property (readonly) NSString *key __attribute__((swift_name("key")));
@property (readonly) int32_t max __attribute__((swift_name("max")));
@property (readonly) int32_t min __attribute__((swift_name("min")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UiControlsModel.Switch")))
@interface MCCUiControlsModelSwitch : MCCUiControlsModel
- (instancetype)initWithKey:(NSString *)key information:(NSString *)information switchValue:(BOOL)switchValue __attribute__((swift_name("init(key:information:switchValue:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (BOOL)component3 __attribute__((swift_name("component3()")));
- (MCCUiControlsModelSwitch *)doCopyKey:(NSString *)key information:(NSString *)information switchValue:(BOOL)switchValue __attribute__((swift_name("doCopy(key:information:switchValue:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *information __attribute__((swift_name("information")));
@property (readonly) NSString *key __attribute__((swift_name("key")));
@property BOOL switchValue __attribute__((swift_name("switchValue")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BaseStartMultiConfigKt")))
@interface MCCBaseStartMultiConfigKt : MCCBase
+ (id<MCCConfigBase>)startMultiConfigBody:(void (^)(MCCBaseMultiConfig *))body __attribute__((swift_name("startMultiConfig(body:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StartMultiConfigKt")))
@interface MCCStartMultiConfigKt : MCCBase
+ (id<MCCConfigBase>)startMultiConfigRootGroup:(NSString *)rootGroup configViewController:(id<MCCMultiConfigViewController>)configViewController body:(void (^)(MCCBaseMultiConfig *))body __attribute__((swift_name("startMultiConfig(rootGroup:configViewController:body:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AppConfigurationBuilderKt")))
@interface MCCAppConfigurationBuilderKt : MCCBase
+ (NSMutableArray<MCCConfiguration *> *)appConfigConfig:(void (^)(MCCAppConfigurationBuilder *))config __attribute__((swift_name("appConfig(config:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinPair")))
@interface MCCKotlinPair : MCCBase
- (instancetype)initWithFirst:(id _Nullable)first second:(id _Nullable)second __attribute__((swift_name("init(first:second:)"))) __attribute__((objc_designated_initializer));
- (id _Nullable)component1 __attribute__((swift_name("component1()")));
- (id _Nullable)component2 __attribute__((swift_name("component2()")));
- (MCCKotlinPair *)doCopyFirst:(id _Nullable)first second:(id _Nullable)second __attribute__((swift_name("doCopy(first:second:)")));
- (BOOL)equalsOther:(id _Nullable)other __attribute__((swift_name("equals(other:)")));
- (int32_t)hashCode __attribute__((swift_name("hashCode()")));
- (NSString *)toString __attribute__((swift_name("toString()")));
@property (readonly) id _Nullable first __attribute__((swift_name("first")));
@property (readonly) id _Nullable second __attribute__((swift_name("second")));
@end;

#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
