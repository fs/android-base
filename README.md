Gradle-based Android app skeleton [![Build Status](https://travis-ci.org/fs/android-base.png)](https://travis-ci.org/fs/android-base)
================================================
#Configured for:
* JDK 7 + 8
* Gradle 1.10
* Android gradle plugin 0.8.+
* Android build tools 19.0.2

#Gradle plugins:
* [android-apt][1] for adding a compile-time annotation processer dependencies
* [gradle-retrolambda][2] for Java 8 lambda support
* [gradle-android-test-plugin][3] for [robolectric][4] support

#Compiler dependencies:
* [ButterKnife][5] view and view callback injections
* [Dagger][6] lightweight dependency injection with typesafe compile-time checks
* [Lombock][7] POJO boilerplate (getters, setters, etc)
* [Argument][8] boilerplate for `Fragment` arguments
* [Memento][9] boilerplate for retaining data in `Activity` across config changes

#Runtime dependencies:
* [RxJava][10] composable and combinable asynchronous data (event) streams
* [Timber][11] advanced logging
* [Jackson][12] mapping Json to POJO and vice-versa
* [OkHttp][13] http client
* [Picasso][14] async image loading, caching with full error recovery (works with [OkHttp][15])
* [Retrofit][16] declarative REST (works with [RxJava][17], [OkHttp][18] and [Jackson][19])
* [Cupboard][20] SQL boilerplate

#Test dependencies
* [JUnit][21] unit testing for Java
* [Robolectric][22] running Android Unit tests on the JVM
* [Mockito][23] mocking framework
* [FEST Android][24] a set of FEST assertions for Android

#Analytics
* [Crittercism][25] crash reporting
* [Google Analytics][26] general analytics

#IDE Enhancements:
* [IntelliJ IDEA Annotations][27] advanced null-pointer, magic constant and other inspections for IntelliJ IDEA-based IDEs


  [1]: https://bitbucket.org/hvisser/android-apt
  [2]: https://github.com/evant/gradle-retrolambda
  [3]: https://github.com/JakeWharton/gradle-android-test-plugin
  [4]: https://github.com/robolectric/robolectric
  [5]: https://github.com/JakeWharton/butterknife
  [6]: https://github.com/square/dagger
  [7]: https://github.com/rzwitserloot/lombok
  [8]: https://bitbucket.org/hvisser/bundles
  [9]: https://github.com/mttkay/memento
  [10]: https://github.com/Netflix/RxJava
  [11]: https://github.com/JakeWharton/timber
  [12]: https://github.com/FasterXML/jackson
  [13]: https://github.com/square/okhttp
  [14]: https://github.com/square/picasso
  [15]: https://github.com/square/okhttp
  [16]: https://github.com/square/retrofit
  [17]: https://github.com/Netflix/RxJava
  [18]: https://github.com/square/okhttp
  [19]: https://github.com/FasterXML/jackson
  [20]: https://bitbucket.org/qbusict/cupboard
  [21]: https://github.com/junit-team/junit
  [22]: https://github.com/robolectric/robolectric
  [23]: https://github.com/mockito/mockito
  [24]: https://github.com/square/fest-android
  [25]: https://www.crittercism.com/
  [26]: http://www.google.com/analytics/
  [27]: https://www.jetbrains.com/idea/documentation/howto.html