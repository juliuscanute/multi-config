#!/bin/bash
shorten () {
	local helpstring="Truncate each line of the input to X characters\n\t-l              Shorten from left side\n\t-s STRING         replace truncated characters with STRING\n\n\t$ ls | shorten -s ... 15"
	local ellip="" left=false
	OPTIND=1
	while getopts "hls:" opt; do
		case $opt in
			l) left=true ;;
			s) ellip=$OPTARG ;;
			h) echo -e $helpstring; return;;
			*) return 1;;
		esac
	done
	shift $((OPTIND-1))

	if $left; then
		cat | sed -E "s/.*(.{${1-70}})$/${ellip}\1/"
	else
		cat | sed -E "s/(.{${1-70}}).*$/\1${ellip}/"
	fi
}

buildFramework(){
  IOS_SDK=$1
  CONFIGURATION=$2
  xcodebuild -sdk "${IOS_SDK}" -configuration "${CONFIGURATION}" -workspace multiconfig-ios/multiconfig-ios.xcworkspace -scheme multiconfig-ios build CODE_SIGNING_ALLOWED=NO | shorten -s ... 80
  export BUILD_DIR=$(xcodebuild -scheme multiconfig-ios -workspace multiconfig-ios/multiconfig-ios.xcworkspace ONLY_ACTIVE_ARCH=NO -sdk "${IOS_SDK}" -configuration "${CONFIGURATION}" -showBuildSettings | grep -m 1 "BUILT_PRODUCTS_DIR" | grep -oEi "\/.*")
}

buildClean(){
  IOS_SDK=$1
  CONFIGURATION=$2
  xcodebuild -sdk "${IOS_SDK}" -configuration "${CONFIGURATION}" -workspace multiconfig-ios/multiconfig-ios.xcworkspace -scheme multiconfig-ios clean CODE_SIGNING_ALLOWED=NO | shorten -s ... 80
}

buildUniversal(){
  SIM_DIR=$1
  IOS_DIR=$2
  CONFIGURATION=$3
  cp -f "$SIM_DIR"/MultiConfig.framework/Modules/MultiConfig.swiftmodule/* "$IOS_DIR"/MultiConfig.framework/Modules/MultiConfig.swiftmodule/ | echo
  lipo -create "$SIM_DIR"/MultiConfig.framework/MultiConfig "$IOS_DIR"/MultiConfig.framework/MultiConfig -output "$IOS_DIR"/MultiConfig.framework/MultiConfig
  lipo -create "$SIM_DIR"/MultiConfigCommon.framework/MultiConfigCommon "$IOS_DIR"/MultiConfigCommon.framework/MultiConfigCommon -output "$IOS_DIR"/MultiConfigCommon.framework/MultiConfigCommon
  (cd "$IOS_DIR" ; zip -r MultiConfig-"${CONFIGURATION}".zip ./MultiConfig.framework)
  cp "$IOS_DIR/MultiConfig-${CONFIGURATION}.zip" .
  (cd "$IOS_DIR" ; zip -r MultiConfigCommon-"${CONFIGURATION}".zip ./MultiConfigCommon.framework)
  cp "$IOS_DIR/MultiConfigCommon-${CONFIGURATION}.zip" .
}

export JAVA_HOME=`/usr/libexec/java_home -v 1.8.0_252`
export ANDROID_SDK_ROOT="$(brew --prefix)/share/android-sdk"
/usr/libexec/java_home -V
echo "$JAVA_HOME"
java -version
yes |  /usr/local/share/android-sdk/tools/bin/sdkmanager --licenses

buildFramework iphonesimulator Debug
export BUILD_SIM_DIR="$BUILD_DIR"
buildFramework iphoneos Debug
export BUILD_IOS_DIR="$BUILD_DIR"
buildUniversal "$BUILD_SIM_DIR" "$BUILD_IOS_DIR" Debug

buildClean iphonesimulator Debug
buildClean iphoneos Debug

buildFramework iphonesimulator Release
export BUILD_SIM_DIR="$BUILD_DIR"
buildFramework iphoneos Release
export BUILD_IOS_DIR="$BUILD_DIR"
buildUniversal "$BUILD_SIM_DIR" "$BUILD_IOS_DIR" Release