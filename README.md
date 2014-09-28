Android app skeleton [![Build Status](https://travis-ci.org/fs/android-base.png)](https://travis-ci.org/fs/android-base)
============================================
##Prerequisites
* JDK 7 and 8 (you need both)
* `JAVA7_HOME` pointing to your jdk7
* `JAVA8_HOME` pointing to your jdk8
* Advanced [Dagger](http://square.github.io/dagger/) knowledge

##What's included:
* [Staging and Production](https://github.com/fs/android-base/blob/master/app/build.gradle#L33-L42) build flavors with different package names
* *[Crashlytics](https://crashlytics.com)* [configuration](https://github.com/fs/android-base/blob/master/app/src/main/AndroidManifest.xml#L30-L32)
* *[MixPanel](https://mixpanel.com/)* [configuration](https://github.com/fs/android-base/blob/master/app/src/main/res/values/tokens.xml#L3)
* Logger configuration [supporting *Crashlytics* `Exception` logging](https://github.com/fs/android-base/blob/master/app/src/main/java/com/flatstack/android/utils/TimberCrashReportingTree.java)
* [Java 8 lambdas support and configuratiuon](https://github.com/fs/android-base/blob/master/app/build.gradle#L87-L91)
* [Robolectric support and configuration](https://github.com/robolectric/robolectric-gradle-plugin)
* Dagger configuration:
	* `Application` subclass with an application-wide scope
	* `Activity` subclass with configured UI-wide scope and a root `Fragment`
	* `ScopedFragment` for `Fragment`-wide scopes
* Default `Menu` with *Settings* `MenuItem`
* `Preferences` interface for the `SharedPreferences` boilerplate reduction
* `PreferenceFragment` with default Preferences xml added to a `MainFragment`'s *Settings* `MenuItem`
* *Android Lint* configuration
* *Travis CI* build script:
    * Downloading an *Android SDK*
    * Building
    * Running *Android Lint*
    * Running *Robolectric* tests
    * Uploading a successful build to the *Crashlytics beta*
* Release build signing and naming configuration

##Setup
 1. Clone application as new project with original remote named "android-base"

    	git clone git://github.com/fs/android-base.git --origin android-base [MY-NEW-PROJECT]

 2. Create your new repository on the GitHub and push master into it. Make sure master branch is tracking origin repo.

        cd [MY-NEW-PROJECT]
    	git remote add origin git@github.com:[MY-GITHUB-ACCOUNT]/[MY-NEW-PROJECT].git
    	git push -u origin master

 3. Import the project into your favourite IDE (only [Android Studio](https://developer.android.com/sdk/installing/studio.html) and [IntelliJ IDEA 13](http://www.jetbrains.com/idea/) are supported at the moment).
Just select the root `build.gradle` and your IDE will do the rest.
It will ask you to change the language level - do it, we're using Java 8 now

## Configuration
There're things that need to be configured (such as app tokens and so on)
`// TODO`

###Making a release build
`// TODO`

##Notes on ProGuarding
Currently [Dagger](http://square.github.io/dagger/) doesn't work with ProGuard at all, but there's a project that solves the problem, and we need to somehow make it work on gradle: https://github.com/idamobile/dagger-proguard-helper

## Credits

Android app skeleton is maintained by [Nizamutdinov Adel](http://github.com/adelnizamutdinov).
It was written by [Flatstack](http://www.flatstack.com) with the help of our
[contributors](http://github.com/fs/android-base/contributors).


[![Flatstack](https://avatars0.githubusercontent.com/u/15136?v=2&s=200)](http://www.flatstack.com)
