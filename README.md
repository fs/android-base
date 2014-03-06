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
###Gradle plugins:
* [android-apt][6] for adding a compile-time annotation processer dependencies
* [gradle-retrolambda][7] for Java 8 lambda support
* [gradle-android-test-plugin][8] for [robolectric][9] support

###Compiler dependencies:
* [ButterKnife][10] view and view callback injections
* [Dagger][11] lightweight dependency injection with typesafe compile-time checks
* [Lombok][12] POJO boilerplate (getters, setters, etc)
* [Argument][13] boilerplate for `Fragment` arguments
* [Memento][14] boilerplate for retaining data in `Activity` across config changes

###Runtime dependencies:
* [RxJava][15] composable and combinable asynchronous data (event) streams
* [Timber][16] advanced logging
* [Jackson][17] mapping Json to POJO and vice-versa
* [OkHttp][18] http client
* [Picasso][19] async image loading, caching with full error recovery (works with [OkHttp][20])
* [Retrofit][21] declarative REST (works with [RxJava][22], [OkHttp][23] and [Jackson][24])
* [Cupboard][25] SQL boilerplate

###Test dependencies
* [JUnit][26] unit testing for Java
* [Robolectric][27] running Android Unit tests on the JVM
* [Mockito][28] mocking framework
* [FEST Android][29] a set of FEST assertions for Android

###Analytics
* [Crittercism][30] crash reporting
* [Google Analytics][31] general analytics

###IDE Enhancements:
* [IntelliJ annotations][32] advanced null-pointer, magic constant and other inspections for IntelliJ IDEA-based IDEs
* [Dagger IntelliJ plugin][33] easily navigate between dependency providers and consumers
* [Lombok IntelliJ plugin][34] codegen support for [Lombok][35] 

## TODO add note on DI
## TODO add note on test dependencies
## TODO add TestFairy or Deploygate gradle tasks
## TODO add note on Travis configuration

  [1]: http://developer.android.com/sdk/installing/studio.html
  [2]: http://www.jetbrains.com/idea/
  [3]: app/src/main/res/values/analytics.xml
  [4]: app/src/main/res/values/tokens.xml
  [5]: app/src/main/AndroidManifest.xml
  [6]: https://bitbucket.org/hvisser/android-apt
  [7]: https://github.com/evant/gradle-retrolambda
  [8]: https://github.com/JakeWharton/gradle-android-test-plugin
  [9]: https://github.com/robolectric/robolectric
  [10]: https://github.com/JakeWharton/butterknife
  [11]: https://github.com/square/dagger
  [12]: https://github.com/rzwitserloot/lombok
  [13]: https://bitbucket.org/hvisser/bundles
  [14]: https://github.com/mttkay/memento
  [15]: https://github.com/Netflix/RxJava
  [16]: https://github.com/JakeWharton/timber
  [17]: https://github.com/FasterXML/jackson
  [18]: https://github.com/square/okhttp
  [19]: https://github.com/square/picasso
  [20]: https://github.com/square/okhttp
  [21]: https://github.com/square/retrofit
  [22]: https://github.com/Netflix/RxJava
  [23]: https://github.com/square/okhttp
  [24]: https://github.com/FasterXML/jackson
  [25]: https://bitbucket.org/qbusict/cupboard
  [26]: https://github.com/junit-team/junit
  [27]: https://github.com/robolectric/robolectric
  [28]: https://github.com/mockito/mockito
  [29]: https://github.com/square/fest-android
  [30]: https://www.crittercism.com
  [31]: http://www.google.com/analytics
  [32]: https://www.jetbrains.com/idea/documentation/howto.html
  [33]: https://github.com/square/dagger-intellij-plugin
  [34]: https://code.google.com/p/lombok-intellij-plugin
  [35]: https://github.com/rzwitserloot/lombok