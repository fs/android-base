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
* [Esperandro][15] shared preferences boilerplate

###Runtime dependencies:
* [RxJava][16] composable and combinable asynchronous data (event) streams
* [Timber][17] advanced logging
* [Jackson][18] mapping Json to POJO and vice-versa
* [OkHttp][19] http client
* [Picasso][20] async image loading, caching with full error recovery (works with [OkHttp][21])
* [Retrofit][22] declarative REST (works with [RxJava][23], [OkHttp][24] and [Jackson][25])
* [Cupboard][26] SQL boilerplate

###Test dependencies
* [JUnit][27] unit testing for Java
* [Robolectric][28] running Android Unit tests on the JVM
* [Mockito][29] mocking framework
* [FEST Android][30] a set of FEST assertions for Android

###Analytics
* [Crittercism][31] crash reporting
* [Google Analytics][32] general analytics

###IDE Enhancements:
* [IntelliJ annotations][33] advanced null-pointer, magic constant and other inspections for IntelliJ IDEA-based IDEs
* [Dagger IntelliJ plugin][34] easily navigate between dependency providers and consumers
* [Lombok IntelliJ plugin][35] codegen support for [Lombok][36]

  [6]: https://bitbucket.org/hvisser/android-apt
  [7]: https://github.com/evant/gradle-retrolambda
  [8]: https://github.com/JakeWharton/gradle-android-test-plugin
  [9]: https://github.com/robolectric/robolectric
  [10]: https://github.com/JakeWharton/butterknife
  [11]: https://github.com/square/dagger
  [12]: https://github.com/rzwitserloot/lombok
  [13]: https://bitbucket.org/hvisser/bundles
  [14]: https://github.com/mttkay/memento
  [15]: https://github.com/dkunzler/esperandro
  [16]: https://github.com/Netflix/RxJava
  [17]: https://github.com/JakeWharton/timber
  [18]: https://github.com/FasterXML/jackson
  [19]: https://github.com/square/okhttp
  [20]: https://github.com/square/picasso
  [21]: https://github.com/square/okhttp
  [22]: https://github.com/square/retrofit
  [23]: https://github.com/Netflix/RxJava
  [24]: https://github.com/square/okhttp
  [25]: https://github.com/FasterXML/jackson
  [26]: https://bitbucket.org/qbusict/cupboard
  [27]: https://github.com/junit-team/junit
  [28]: https://github.com/robolectric/robolectric
  [29]: https://github.com/mockito/mockito
  [30]: https://github.com/square/fest-android
  [31]: https://www.crittercism.com
  [32]: http://www.google.com/analytics
  [33]: https://www.jetbrains.com/idea/documentation/howto.html
  [34]: https://github.com/square/dagger-intellij-plugin
  [35]: https://code.google.com/p/lombok-intellij-plugin
  [36]: https://github.com/rzwitserloot/lombok