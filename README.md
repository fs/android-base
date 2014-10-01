Android app skeleton [![Build Status](https://travis-ci.org/fs/android-base.png)](https://travis-ci.org/fs/android-base)
=======================================
##Prerequisites
* JDK 8
* `JAVA_HOME` pointing to your jdk8
* `ANDROID_HOME` pointing to your android-sdk

##What's included:
* [Staging and Production](https://github.com/fs/android-base/blob/master/app/build.gradle#L33-L42) build flavors with different package names ([read more](http://tools.android.com/tech-docs/new-build-system/user-guide#TOC-Product-flavors))
* Logger configuration [supporting `Exception` logging](https://github.com/fs/android-base/blob/master/app/src/main/java/com/flatstack/android/App.java#L24-L26) ([read more](https://github.com/JakeWharton/timber))
* [Java 8 lambdas support and configuratiuon](https://github.com/fs/android-base/blob/master/app/build.gradle#L41-L44) ([read more](https://github.com/evant/gradle-retrolambda))
* [Robolectric support and configuration](https://github.com/fs/android-base/blob/master/app-tests/build.gradle) ([read more](http://blog.blundell-apps.com/android-gradle-app-with-robolectric-junit-tests/))
* [Dagger](http://square.github.io/dagger/) configuration ([read more](http://stackoverflow.com/a/16923040)):
	* [`Application`](https://github.com/fs/android-base/blob/master/app/src/main/java/com/flatstack/android/App.java) subclass with an [application-wide scope](https://github.com/fs/android-base/blob/master/app/src/main/java/com/flatstack/android/dagger/modules/ApplicationScopeModule.java)
	* [`Activity`](https://github.com/fs/android-base/blob/master/app/src/main/java/com/flatstack/android/MainActivity.java) subclass with configured [UI-wide scope](https://github.com/fs/android-base/blob/master/app/src/main/java/com/flatstack/android/dagger/modules/MainActivityScopeModule.java) and a root `Fragment`
	* [`ScopedFragment`](https://github.com/fs/android-base/blob/master/app/src/main/java/com/flatstack/android/dagger/ScopedFragment.java) for `Fragment`-wide [scopes](https://github.com/fs/android-base/blob/master/app/src/main/java/com/flatstack/android/dagger/modules/MainFragmentScopeModule.java)
* Default `Menu` with *Settings* `MenuItem`
* [`Preferences`](https://github.com/fs/android-base/blob/master/app/src/main/java/com/flatstack/android/utils/Preferences.java) interface for the `SharedPreferences` boilerplate reduction (using [Esperandro](http://dkunzler.github.io/esperandro/))
* [`PreferenceFragment`](https://github.com/fs/android-base/blob/master/app/src/main/java/com/flatstack/android/fragments/PrefsFragment.java) with default Preferences xml added to a `MainFragment`'s *Settings* `MenuItem`
* *Android Lint* [configuration](https://github.com/fs/android-base/blob/master/app/build.gradle#L56-L61)
* *Travis CI* build [script](https://github.com/fs/android-base/blob/master/.travis.yml):
    * Downloading an *Android SDK*
    * Building
    * Running *Android Lint*
    * Running *Robolectric* tests
    * Hook up your continuous deployment target in [`after_success`](https://github.com/fs/android-base/blob/master/.travis.yml#L21)
* Release build signing and naming configuration

##What's not included
* [Crashlytics](crashlytics.com): they live in their own world, and including their plugin in template project just fails the build, if `apikey` is not specified. Also, getting `apikey` without an IDE plugin is impossible. You can get it [here](https://crashlytics.com/downloads/android-studio)
* Test coverage: still in the process of figuring out what's the best way to enable unit test coverage for Android with Robolectric. Any suggestions will be highly appreciated

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

###Configuration
* Change your app's package by either [renaming the folder structure for Java sources](https://github.com/fs/android-base/tree/master/app/src/main/java/com/flatstack/android) or by just changing this [constant](https://github.com/fs/android-base/blob/master/app/build.gradle#L5) in `build.gradle`

###Making a release build
* Just uncomment [these lines](https://github.com/fs/android-base/blob/master/app/build.gradle#L41-L48) and fill them up with your credentials

##Notes on ProGuarding
`TODO`

## Credits
Android app skeleton is maintained by [Adel Nizamutdinov](http://github.com/adelnizamutdinov).
It was written by [Flatstack](http://www.flatstack.com) with the help of our
[contributors](http://github.com/fs/android-base/contributors)

[![Flatstack](https://avatars0.githubusercontent.com/u/15136?v=2&s=200)](http://www.flatstack.com)
