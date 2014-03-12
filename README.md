Android app skeleton [![Build Status](https://travis-ci.org/fs/android-base.png)](https://travis-ci.org/fs/android-base)
================================================
##Prerequisites
* JDK 7 and 8 (you need both)
* Android SDK tools 22.6
* Android build tools 19.0.3
* `JAVA7_HOME` pointing to your jdk7
* `JAVA8_HOME` pointing to your jdk8
* `ANDROID_HOME` pointing to your android sdk

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

##Configuration
There's things that need to be configured (such as app tokens and so on)

1. At [app/src/main/res/values/analytics.xml][3] change your Google Analytics `ga_trackingId` appropriately, or completely remove GA if you don't need it (see *removing GA section*)
2. At [app/src/main/res/values/tokens.xml][4] change `crittercism_app_id`, or completely remove it if not needed (see *removing Crittercism section*)
3. At [app/src/main/AndroidManifest.xml][5] change the `package` field how you need it 
4. Now the toughest part - renaming the default `com.companyname.appname` java package
    1. At the project view, click on a gear icon and unselect *Compact empty middle packages*, this will allow us to rename the empty packages
    2. Now select any package root, `companyname`, for example. Highlight it and do `Refactor` -> `Rename` -> `Rename package`. This will rename the package root and all the underlying packages, imports and also do that for `test`source folder
    3. Click the gear icon and select *Hide empty middle packages* again

##What's included:


## TODO add note on DI
## TODO add note on test dependencies
## TODO add TestFairy or Deploygate gradle tasks
## TODO add note on Travis configuration


  [1]: http://developer.android.com/sdk/installing/studio.html
  [2]: http://www.jetbrains.com/idea/

 