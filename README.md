Android app skeleton [![Build Status](https://travis-ci.org/fs/android-base.png)](https://travis-ci.org/fs/android-base)
================================================
##Prerequisites
* JDK 7 and 8 (you need both)
* Android SDK tools 22.6
* Android build tools 19.0.3
* `JAVA7_HOME` pointing to your jdk7
* `JAVA8_HOME` pointing to your jdk8
* `ANDROID_HOME` pointing to your android sdk

##What's included:
* *Crittercism* configuration
* *Google Analytics* configuration with `Fragment` tracking helper methods
* Logger configuration supporting *Crittercism* `Exception` logging
* Java 8 lambdas support and configuratiuon
* Robolectric support and configuration
* `Application` subclass with an application-wide `ObjectGraph`
* `Activity` subclass with configured UI-wide `ObjectGraph` and a root `Fragment`
* Default `Menu` with *Settings* `MenuItem`
* `Preferences` interface for the `SharedPreferences` boilerplate reduction
* `PreferenceFragment` with default Preferences xml added to a `MainFragment`'s *Settings* `MenuItem`
* *Android Lint* configuration
* *TestFairy* upload script
* *Travis CI* build script, including:

    * Downloading an *Android SDK*
    * Building
    * Running *Android Lint*
    * Running *Robolectric* tests
    * Uploading a successful build to the *TestFairy*
* Release build signing and naming configuration

##[Bundled dependencies][1]

##Setup
 1. Clone application as new project with original remote named "android-base"

    	git clone git://github.com/fs/android-base.git --origin android-base [MY-NEW-PROJECT]

 2. Create your new repository on the GitHub and push master into it. Make sure master branch is tracking origin repo.

        cd [MY-NEW-PROJECT]
    	git remote add origin git@github.com:[MY-GITHUB-ACCOUNT]/[MY-NEW-PROJECT].git
    	git push -u origin master

 3. Import the project into your IDE (only [Android Studio][2] and [IntelliJ IDEA 13][3] are supported at the moment).
Just select the root `build.gradle` and your IDE will do the rest.
It will ask you to change the language level - do it, we're using Java 8 now

##Configuration
There're things that need to be configured (such as app tokens and so on)

1. At the [analytics.xml][4] change your Google Analytics `ga_trackingId` appropriately, and uncomment [these lines in `MainActivity.java`][5]
2. At the [tokens.xml][6] change `crittercism_app_id`, and uncomment [this line in `App.java`][7]
3. At the [AndroidManifest.xml][8] change the `package` field to the desired one
4. Last but not least, don't forget to change an [app icon][9] and an [app name][10]

###Uploading to the TestFairy
You need to add your API key and the tester groups to the [upload script][11]
###Making a release build
You need to uncomment [these lines in `build.gradle`][12] and also add your keystore credentials to the [`gradle.properties`][13]
##Notes on ProGuarding
Currently [Dagger][14] doesn't work with ProGuard at all, but there's a project that solves the problem, and we need to somehow make it work on gradle: https://github.com/idamobile/dagger-proguard-helper


  [1]: DEPENDENCIES.md
  [2]: http://developer.android.com/sdk/installing/studio.html
  [3]: http://www.jetbrains.com/idea/
  [4]: app/src/main/res/values/analytics.xml
  [5]: app/src/main/java/com/flatsoft/base/MainActivity.java#L55-L63
  [6]: app/src/main/res/values/tokens.xml
  [7]: app/src/main/java/com/flatsoft/base/App.java#L30
  [8]: app/src/main/AndroidManifest.xml
  [9]: app/src/main/res/drawable-xhdpi/ic_launcher.png
  [10]: app/src/main/res/values/strings.xml#L3
  [11]: testfairy-upload.sh#L4-L5
  [12]: app/build.gradle#L33-L50
  [13]: app/gradle.properties
  [14]: https://github.com/square/dagger