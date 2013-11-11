#################################################################################
# Use this script to update to new Chromium source and build artifacts
# This assumes you have a Linux build machine and you've already compiled
# Chromium Content Shell and Test Shell
#
# ninja -c out/Release -k0 -j4 libwebviewchromium content_shell_apk chromium_testshell
#################################################################################

# point this to the build server's chromium/src/ dir so that scp commands will work
export BUILD=crbuild@ubuntu.local:chromium/src
# set this to the root chromium/ directory
export BASE=..

#-------------------------------------------------------------------------------
#  JARS WE NEED
#-------------------------------------------------------------------------------
scp $BUILD/out/Release/lib.java/jsr_305_javalib.jar         $BASE/.m2/javax/annotations/jsr305/1.0/jsr305-1.0.jar

scp $BUILD/out/Release/lib.java/guava_javalib.jar           $BASE/.m2/com/google/common/guava/1.0/guava-1.0.jar

scp $BUILD/third_party/android_tools/sdk/extras/google/gcm/gcm-client/dist/gcm.jar \
                                                            $BASE/.m2/com/google/gcm/1.0.2/gcm-1.0.2.jar

#-------------------------------------------------------------------------------
#  BASE
#-------------------------------------------------------------------------------
export PROJ=$BASE/base

rm -rf $PROJ/src/main/java/*

# sources
scp -r $BUILD/base/android/java/src/*                       $PROJ/src/main/java/
scp -r $BUILD/out/Release/gen/templates/org/chromium/base/* $PROJ/src/main/java/org/chromium/base/

#-------------------------------------------------------------------------------
#  EYESFREE
#-------------------------------------------------------------------------------
export PROJ=$BASE/eyesfree

rm -rf $PROJ/src/main/java/*
rm -rf $PROJ/src/main/aidl/com/googlecode/eyesfree/braille/display/*
rm -rf $PROJ/src/main/aidl/com/googlecode/eyesfree/selfbraille/*
rm -rf $PROJ/src/main/aidl/com/googlecode/eyesfree/translate/*

# sources
scp -r $BUILD/third_party/eyesfree/src/android/java/src/*   $PROJ/src/main/java/

# delete .git/ directory
rm -rf $PROJ/src/main/java/com/googlecode/eyesfree/braille/.git/

# move .aidl files to proper home
mv $PROJ/src/main/java/com/googlecode/eyesfree/braille/display/*.aidl $PROJ/src/main/aidl/com/googlecode/eyesfree/braille/display/
mv $PROJ/src/main/java/com/googlecode/eyesfree/braille/selfbraille/*.aidl $PROJ/src/main/aidl/com/googlecode/eyesfree/braille/selfbraille/
mv $PROJ/src/main/java/com/googlecode/eyesfree/braille/translate/*.aidl $PROJ/src/main/aidl/com/googlecode/eyesfree/braille/translate/

#-------------------------------------------------------------------------------
#  MEDIA
#-------------------------------------------------------------------------------
export PROJ=$BASE/media

rm -rf $PROJ/src/main/java/*

# sources
scp -r $BUILD/media/base/android/java/src/*                 $PROJ/src/main/java/
scp $BUILD/out/Release/gen/templates/org/chromium/media/*   $PROJ/src/main/java/org/chromium/media/

# one of the generated files has the wrong class name ImageFormat => AndroidImageFormatList
mv $PROJ/src/main/java/org/chromium/media/ImageFormat.java  $PROJ/src/main/java/org/chromium/media/AndroidImageFormatList.java

#-------------------------------------------------------------------------------
#  NET
#-------------------------------------------------------------------------------
export PROJ=$BASE/net

rm -rf $PROJ/src/main/java/*

scp -r $BUILD/net/android/java/src/*                        $PROJ/src/main/java/
scp -r $BUILD/out/Release/gen/templates/org/chromium/net/*  $PROJ/src/main/java/org/chromium/net/

#-------------------------------------------------------------------------------
#  UI
#-------------------------------------------------------------------------------
export PROJ=$BASE/ui

rm -rf $PROJ/src/main/java/*
rm -rf $PROJ/src/main/res/*

scp -r $BUILD/ui/android/java/src/*                         $PROJ/src/main/java/
scp -r $BUILD/ui/android/java/res/*                         $PROJ/src/main/res/
scp -r $BUILD/out/Release/gen/ui_java/res_grit/values/*     $PROJ/src/main/res/values/

scp -r $BUILD/out/Release/gen/templates/org/chromium/ui/*   $PROJ/src/main/java/org/chromium/ui/

#-------------------------------------------------------------------------------
#  CONTENT
#-------------------------------------------------------------------------------
export PROJ=$BASE/content

rm -rf $PROJ/src/main/java/*
rm -rf $PROJ/src/main/res/*
rm $PROJ/src/main/aidl/org/chromium/content/content/*

scp -r $BUILD/content/public/android/java/src/*             $PROJ/src/main/java
scp -r $BUILD/content/public/android/java/res/*             $PROJ/src/main/res/
scp -r $BUILD/out/Release/gen/content_java/res_grit/values/* \
                                                            $PROJ/src/main/res/values/
scp -r $BUILD/out/Release/gen/templates/org/chromium/content/* \
                                                            $PROJ/src/main/java/org/chromium/content/

mv $PROJ/src/main/java/org/chromium/content/common/*.aidl $PROJ/src/main/aidl/org/chromium/content/common/
# delete the file common.aidl b/c it will not compile
rm $PROJ/src/main/aidl/org/chromium/content/common/common.aidl

echo "FIXME: diff and hand-merge the tree under $PROJ/modified/ into $PROJ/src/"

#-------------------------------------------------------------------------------
#  SYNC
#-------------------------------------------------------------------------------
export PROJ=$BASE/sync

rm -rf $PROJ/src/main/java/*

# sources
scp -r $BUILD/sync/android/java/src/*                       $PROJ/src/main/java/

#-------------------------------------------------------------------------------
#  CACHEINVALIDATION
#-------------------------------------------------------------------------------
export PROJ=$BASE/cacheinvalidation

rm -rf $PROJ/src/main/java/*

# sources
scp -r $BUILD/third_party/cacheinvalidation/src/java/*      $PROJ/src/main/java/
scp -r $BUILD/out/Release/java_proto/cacheinvalidation_proto_java/src/* \
                                                            $PROJ/src/main/java/

# aidl already generated
mkdir -p $PROJ/src/main/java/com/google/ipc/invalidation/external/client/android/service
scp -r $BUILD/out/Release/gen/cacheinvalidation_aidl_javalib/aidl/* \
                                                            $PROJ/src/main/java/com/google/ipc/invalidation/external/client/android/service/

# These seem to be already generated into .java, and including them under src/main/aidl causes compilation error => duplicate class
# ./src/main/java/com/google/ipc/invalidation/external/client/android/service/InvalidationService.aidl
# ./src/main/java/com/google/ipc/invalidation/external/client/android/service/ListenerService.aidl
# ./src/main/java/com/google/ipc/invalidation/testing/android/InvalidationTest.aidl
rm $PROJ/src/main/java/com/google/ipc/invalidation/external/client/android/service/*.aidl
rm $PROJ/src/main/java/com/google/ipc/invalidation/testing/android/*.aidl

#-------------------------------------------------------------------------------
#  PROTOBUF
#-------------------------------------------------------------------------------
export PROJ=$BASE/protobuf

rm -rf $PROJ/src/main/java/*

# sources
scp -r $BUILD/third_party/protobuf/java/src/main/java/*     $PROJ/src/main/java/
scp -r $BUILD/out/Release/java_proto/protobuf_lite_java_descriptor_proto/* \
                                                            $PROJ/src/main/java/

#-------------------------------------------------------------------------------
#  COMPONENTS
#-------------------------------------------------------------------------------
export PROJ=$BASE/components

rm -rf $PROJ/src/main/java/*

# sources
scp -r $BUILD/components/web_contents_delegate_android/android/java/src/* \
                                                            $PROJ/src/main/java/
scp -r $BUILD/components/navigation_interception/android/java/src/* \
                                                            $PROJ/src/main/java/
scp -r $BUILD/components/autofill/core/browser/android/java/src/* \
                                                            $PROJ/src/main/java/

#-------------------------------------------------------------------------------
#  CHROME
#-------------------------------------------------------------------------------
export PROJ=$BASE/chrome

rm -rf $PROJ/src/main/java/*
rm -rf $PROJ/src/main/res/*

# sources
scp -r $BUILD/chrome/android/java/src/*                     $PROJ/src/main/java/
scp -r $BUILD/out/Release/gen/templates/org/chromium/chrome/* \
                                                            $PROJ/src/main/java/org/chromium/chrome/

# resources - order matters here!

# these are generated

# these are the values/generated_resources.xml
scp -r $BUILD/out/Release/gen/chrome/java/res/*             $PROJ/src/main/res/

# these are also values/android_chrome_strings.xml
scp -r $BUILD/out/Release/gen/chrome_java/res_grit/*        $PROJ/src/main/res/

# these are drawables/ and modified by process for 9-patch I think; overwrites the stock from above
scp -r $BUILD/out/Release/gen/chrome_java/res_crunched/*    $PROJ/src/main/res/

# these are the stock resources
scp -r $BUILD/chrome/android/java/res/*                     $PROJ/src/main/res/

echo "FIXME: last time, bubble.9.png had problems and needed to be manually fixed, see bubble.9.zip in $PROJ/"

#-------------------------------------------------------------------------------
#  TESTSHELL
#-------------------------------------------------------------------------------
export PROJ=$BASE/testshell

rm -rf $PROJ/src/main/java/*
rm -rf $PROJ/src/main/res/*

# copy the native library
scp $BUILD/out/Release/chromium_testshell/libs/armeabi-v7a/libchromiumtestshell.so \
                                                            $PROJ/libs/armeabi-v7a/
# copy the pak files
scp -r $BUILD/out/assets/chromium_testshell/*               $PROJ/src/main/assets/

# copy the manifest file
scp $BUILD/out/Release/chromium_testshell/AndroidManifest.xml \
                                                            $PROJ/src/main/
# copy the sources and resources
scp -r $BUILD/chrome/android/testshell/java/src/*           $PROJ/src/main/java/
scp -r $BUILD/chrome/android/testshell/res/*                $PROJ/src/main/res/

echo "FIXME: diff and hand-merge the tree under $PROJ/modified/ into $PROJ/src/"

#-------------------------------------------------------------------------------
#  CONTENT-SHELL
#-------------------------------------------------------------------------------
export PROJ=$BASE/content-shell

rm -rf $PROJ/src/main/java/*
rm -rf $PROJ/src/main/res/*

# copy the native library
scp $BUILD/out/Release/content_shell_apk/libs/armeabi-v7a/libcontent_shell_content_view.so \
                                                            $PROJ/libs/armeabi-v7a/
# copy the pak file
scp $BUILD/out/Release/content_shell/assets/content_shell.pak \
                                                            $PROJ/src/main/assets/
# copy the manifest file
scp $BUILD/content/shell/android/shell_apk/AndroidManifest.xml $PROJ/src/main/

# copy org.chromium.content_shell_apk.* sources and resources
scp -r $BUILD/content/shell/android/shell_apk/src/*         $PROJ/src/main/java/
scp -r $BUILD/content/shell/android/shell_apk/res/*         $PROJ/src/main/res/

# copy org.chromium.content_shell.* sources and resources
scp -r $BUILD/content/shell/android/java/src/*              $PROJ/src/main/java/
scp -r $BUILD/content/shell/android/java/res/*              $PROJ/src/main/res/

# move the java sources from content_shell_apk/ to content_shell/
mv $PROJ/src/main/java/org/chromium/content_shell_apk/*     $PROJ/src/main/java/org/chromium/content_shell/
# delete old content_shell_apk/ dir
rm -rf $PROJ/src/main/java/org/chromium/content_shell_apk/

echo "FIXME: diff and hand-merge the tree under $PROJ/modified/ into $PROJ/src/"
echo "FIXME: you also need to move all classes under src/main/java to package org.chromium.content_shell.*"

#-------------------------------------------------------------------------------
#  ANDROID-WEBVIEW
#-------------------------------------------------------------------------------
export PROJ=$BASE/android-webview

rm -rf $PROJ/src/main/java/*
rm -rf $PROJ/src/main/res/*

# copy the native library
scp $BUILD/out/Release/android_webview_apk/libs/armeabi-v7a/libstandalonelibwebviewchromium.so \
                                                            $PROJ/libs/armeabi-v7a/

# copy the pak files and other assets
scp $BUILD/out/Release/android_webview_apk/assets/*         $PROJ/src/main/assets/
scp $BUILD/out/Release/locales/en-US.pak                    $PROJ/src/main/assets/

# copy the manifest file
scp $BUILD/out/Release/android_webview_apk/AndroidManifest.xml \
                                                            $PROJ/src/main/

# copy the sources and resources
scp -r $BUILD/android_webview/java/src/*                    $PROJ/src/main/java/
scp -r $BUILD/android_webview/test/shell/src/*              $PROJ/src/main/java/
scp -r $BUILD/out/Release/android_webview_apk/res/*         $PROJ/src/main/res/
scp -r $BUILD/android_webview/test/shell/res/*              $PROJ/src/main/res/

echo "FIXME: diff and hand-merge the tree under $PROJ/modified/ into $PROJ/src/"

#-------------------------------------------------------------------------------
#  GENERAL CLEANUP
#-------------------------------------------------------------------------------

# these are code-generation sources no longer necessary in the source tree
find $BASE -name "*.template" -depth -exec rm {} \;

find $BASE -name ".DS_Store" -depth -exec rm {} \;
