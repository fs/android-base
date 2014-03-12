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
 1. *Crittercism* configuration
 2. *Google Analytics* configuration with `Fragment` tracking helper methods
 3. Logger configuration supporting *Crittercism* `Exception` logging
 4. Java 8 lambdas support and configuratiuon
 5. Robolectric support and configuration
 6. `Application` subclass with an application-wide `ObjectGraph`
 7. `Activity` subclass with configured UI-wide `ObjectGraph` and a root `Fragment`
 8. Default `Menu` with *Settings* `MenuItem`
 9. `Preferences` interface for the `SharedPreferences` boilerplate reduction
 10. `PreferenceFragment` with default Preferences xml added to a `MainFragment`'s *Settings* `MenuItem`
 11. *Android Lint* configuration
 12. *TestFairy* upload script
 13. *Travis CI* build script, including:
    * Downloading an *Android SDK*
    * Building
    * Running *Android Lint*
    * Running *Robolectric* tests
    * Uploading a successful build to the *TestFairy*
 14. Release build signing and naming configuration

##Setup
 1. Clone application as new project with original remote named "android-base"

    	git clone git://github.com/fs/android-base.git --origin android-base [MY-NEW-PROJECT]

 2. Create your new repository on the GitHub and push master into it. Make sure master branch is tracking origin repo.

        cd [MY-NEW-PROJECT]
    	git remote add origin git@github.com:[MY-GITHUB-ACCOUNT]/[MY-NEW-PROJECT].git
    	git push -u origin master

 3. Import the project into your IDE (only [Android Studio][1] and [IntelliJ IDEA 13][2] are supported at the moment).
Just select the root `build.gradle` and your IDE will do the rest.
It will ask you to change the language level - do it, we're using Java 8 now

##[Configuration][3]
         
  [1]: http://developer.android.com/sdk/installing/studio.html
  [2]: http://www.jetbrains.com/idea/
  [3]: CONFIGURATION.md