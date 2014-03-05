Android app skeleton [![Build Status](https://travis-ci.org/fs/android-base.png)](https://travis-ci.org/fs/android-base)
================================================
##Configured for:
* JDK 7 and 8 (you need both)
* Android gradle plugin 0.8.+
* Android build tools 19.0.2

##Prerequisites
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
Just select the root `build.gradle` and your IDE will do the rest
It will ask you to change the language level - do it, we're using Java 8 now

##Configuration
There's things that need to be configured (such as app tokens and so on)

1. At [app/src/main/res/values/analytics.xml][3] change your Google Analytics `ga_trackingId` appropriately, or completely remove GA if you don't need it (see *removing GA section*)
2. At [app/src/main/res/values/tokens.xml][4] change `crittercism_app_id`, or completely remove it if not needed (see *removing Crittercism section*)
3. At `app/src/main/AndroidManifest.xml` change the `package` field how you need it 

##What's included:
###Gradle plugins:
* [android-apt][5] for adding a compile-time annotation processer dependencies
* [gradle-retrolambda][6] for Java 8 lambda support
* [gradle-android-test-plugin][7] for [robolectric][8] support

###Compiler dependencies:
* [ButterKnife][9] view and view callback injections
* [Dagger][10] lightweight dependency injection with typesafe compile-time checks
* [Lombok][11] POJO boilerplate (getters, setters, etc)
* [Argument][12] boilerplate for `Fragment` arguments
* [Memento][13] boilerplate for retaining data in `Activity` across config changes

###Runtime dependencies:
* [RxJava][14] composable and combinable asynchronous data (event) streams
* [Timber][15] advanced logging
* [Jackson][16] mapping Json to POJO and vice-versa
* [OkHttp][17] http client
* [Picasso][18] async image loading, caching with full error recovery (works with [OkHttp][19])
* [Retrofit][20] declarative REST (works with [RxJava][21], [OkHttp][22] and [Jackson][23])
* [Cupboard][24] SQL boilerplate

###Test dependencies
* [JUnit][25] unit testing for Java
* [Robolectric][26] running Android Unit tests on the JVM
* [Mockito][27] mocking framework
* [FEST Android][28] a set of FEST assertions for Android

###Analytics
* [Crittercism][29] crash reporting
* [Google Analytics][30] general analytics

###IDE Enhancements:
* [IntelliJ annotations][31] advanced null-pointer, magic constant and other inspections for IntelliJ IDEA-based IDEs
* [Dagger IntelliJ plugin][32] easily navigate between dependency providers and consumers
* [Lombok IntelliJ plugin][33] codegen support for [Lombok][34] 


  [1]: http://developer.android.com/sdk/installing/studio.html
  [2]: http://www.jetbrains.com/idea/
  [3]: app/src/main/res/values/analytics.xml
  [4]: app/src/main/res/values/tokens.xml
  [5]: https://bitbucket.org/hvisser/android-apt
  [6]: https://github.com/evant/gradle-retrolambda
  [7]: https://github.com/JakeWharton/gradle-android-test-plugin
  [8]: https://github.com/robolectric/robolectric
  [9]: https://github.com/JakeWharton/butterknife
  [10]: https://github.com/square/dagger
  [11]: https://github.com/rzwitserloot/lombok
  [12]: https://bitbucket.org/hvisser/bundles
  [13]: https://github.com/mttkay/memento
  [14]: https://github.com/Netflix/RxJava
  [15]: https://github.com/JakeWharton/timber
  [16]: https://github.com/FasterXML/jackson
  [17]: https://github.com/square/okhttp
  [18]: https://github.com/square/picasso
  [19]: https://github.com/square/okhttp
  [20]: https://github.com/square/retrofit
  [21]: https://github.com/Netflix/RxJava
  [22]: https://github.com/square/okhttp
  [23]: https://github.com/FasterXML/jackson
  [24]: https://bitbucket.org/qbusict/cupboard
  [25]: https://github.com/junit-team/junit
  [26]: https://github.com/robolectric/robolectric
  [27]: https://github.com/mockito/mockito
  [28]: https://github.com/square/fest-android
  [29]: https://www.crittercism.com
  [30]: http://www.google.com/analytics
  [31]: https://www.jetbrains.com/idea/documentation/howto.html
  [32]: https://github.com/square/dagger-intellij-plugin
  [33]: https://code.google.com/p/lombok-intellij-plugin
  [34]: https://github.com/rzwitserloot/lombok