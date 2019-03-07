# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#xiangmu
#1.不优化输入的class文件，默认情况下是启用优化的
-dontoptimize

#2.Proguard对你的代码进行迭代优化的次数 0~7，一直优化到代码不能被优化为止
-optimizationpasses 5

#3.跳过库中非public的类，可以加快proguard处理速度；但是有些类库包含从public类中继承来的非public类。这时如果加了这条会产生一个warning(find classes)
-skipnonpubliclibraryclasses

 #4.指定不去忽略非公共的库类
-dontskipnonpubliclibraryclasses

#5.指定不去忽略包可见的库类的成员。
-dontskipnonpubliclibraryclassmembers

#6.确定统一的混淆类的成员名称来增加混淆
-useuniqueclassmembernames

#7.混淆时不会产生形形色色的类名
   -dontusemixedcaseclassnames

#8.混淆前认证,去掉可加快混淆速度
-dontpreverify

#9.假如项目中有用到注解,保留注解，不混淆
-keepattributes *Annotation*

#android
#1.保留所有的v4包中类不被混淆
 -keep class android.support.v4.**

 #2.保留所有的v4包中接口不被混淆
  -keep interface android.support.v4.**

  #3.保留系统中继承v4/v7包的类，不被混淆
   -keep class android.support.** { *; }
   -keep class android.support.v4.** { *; }
   -keep public class * extends android.support.v4.**
   -keep interface android.support.v4.app.** { *; }
   -keep class android.support.v7.** { *; }
   -keep public class * extends android.support.v7.**
   -keep interface android.support.v7.app.** { *; }
   -dontwarn android.support.**

   #4.保留系统中继实现v4/v7包的接口，不被混淆
    -keep public class * implements android.support.v4.**
    -dontwarn android.support.v4.**

    #5.所有的native方法不被混淆
     -keepclasseswithmembers class * {
         native <methods>;
     }

     #6.自定义View构造方法不混淆

      -keepclasseswithmembers class * {
          public <init>(android.content.Context);
      }
      -keepclasseswithmembers class * {
          public <init>(android.content.Context,android.util.AttributeSet);
      }
      -keepclasseswithmembers class * {
          public <init>(android.content.Context,android.util.AttributeSet,int);
      }

      #7.枚举不被混淆

       -keepclassmembers enum * {
           public static **[] values();
           public static ** valueOf(java.lang.String);
           }

#8.release版不打印log
 -assumenosideeffects class android.util.Log {
     public static *** d(...);
     public static *** v(...);
     public static *** i(...);
     public static *** e(...);
     public static *** w(...);
 }

 #9.四大组件不能混淆

  -dontwarn android.support.v4.**
  -keep class android.support.v4.app.** { *; }
  -keep interface android.support.v4.app.** { *; }
  -keep class android.support.v4.** { *; }
  -keep public class * extends android.app.Application

  -dontwarn android.support.v7.**
  -keep class android.support.v7.internal.** { *; }
  -keep interface android.support.v7.internal.** { *; }
  -keep class android.support.v7.** { *; }

  -keep public class * extends android.app.Activity
  -keep public class * extends android.app.Fragment
  -keep public class * extends android.app.Application
  -keep public class * extends android.app.Service
  -keep public class * extends android.content.BroadcastReceiver
  -keep public class * extends android.content.ContentProvider
  -keep public class * extends android.app.backup.BackupAgentHelper
  -keep public class * extends android.preference.Preference

  #10.Design包不混淆
   -dontwarn android.support.design.**
   -keep class android.support.design.** { *; }
   -keep interface android.support.design.** { *; }
   -keep public class android.support.design.R$* { *; }

   #11.确保JavaBean不被混淆-否则Gson将无法将数据解析成具体对象
    -keep class httploglib.lib.been..** { *; }

    #12.不混淆资源类
     -keepclassmembers class **.R$* {
         public static <fields>;
     }

     #13.忽略警告
 #     -ignorewarning

 #14 webview确保openFileChooser方法不被混淆
 -keepclassmembers class * extends com.tencent.smtt.sdk.WebChromeClient{
  public void openFileChooser(...);
  }


