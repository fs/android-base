###Gradle plugins:
* [android-apt][1] for adding a compile-time annotation processer dependencies
* [gradle-retrolambda][2] for Java 8 lambda support
* [gradle-android-test-plugin][3] for [robolectric][4] support

###Compiler dependencies:
* [ButterKnife][5] view and view callback injections
* [Dagger][6] lightweight dependency injection with typesafe compile-time checks
* [Lombok][7] POJO boilerplate (getters, setters, etc)
* [Argument][8] boilerplate for `Fragment` arguments
* [Memento][9] boilerplate for retaining data in `Activity` across config changes
* [Esperandro][10] shared preferences boilerplate

###Runtime dependencies:
* [RxJava][11] composable and combinable asynchronous data (event) streams
* [Timber][12] advanced logging
* [Jackson][13] mapping Json to POJO and vice-versa
* [OkHttp][14] http client
* [Picasso][15] async image loading, caching with full error recovery (works with [OkHttp][16])
* [Retrofit][17] declarative REST (works with [RxJava][18], [OkHttp][19] and [Jackson][20])
* [Cupboard][21] SQL boilerplate
* [Calligraphy][22] custom fonts: the easy way

###Test dependencies
* [JUnit][23] unit testing for Java
* [Robolectric][24] running Android Unit tests on the JVM
* [Mockito][25] mocking framework
* [FEST Android][26] a set of FEST assertions for Android

###Analytics
* [Crittercism][27] crash reporting
* [Google Analytics][28] general analytics

###IDE Enhancements:
* [IntelliJ annotations][29] advanced null-pointer, magic constant and other inspections for IntelliJ IDEA-based IDEs
* [Dagger IntelliJ plugin][30] easily navigate between dependency providers and consumers
* [Lombok IntelliJ plugin][31] codegen support for [Lombok][32]


  [1]: https://bitbucket.org/hvisser/android-apt
  [2]: https://github.com/evant/gradle-retrolambda
  [3]: https://github.com/JakeWharton/gradle-android-test-plugin
  [4]: https://github.com/robolectric/robolectric
  [5]: https://github.com/JakeWharton/butterknife
  [6]: https://github.com/square/dagger
  [7]: https://github.com/rzwitserloot/lombok
  [8]: https://bitbucket.org/hvisser/bundles
  [9]: https://github.com/mttkay/memento
  [10]: https://github.com/dkunzler/esperandro
  [11]: https://github.com/Netflix/RxJava
  [12]: https://github.com/JakeWharton/timber
  [13]: https://github.com/FasterXML/jackson
  [14]: https://github.com/square/okhttp
  [15]: https://github.com/square/picasso
  [16]: https://github.com/square/okhttp
  [17]: https://github.com/square/retrofit
  [18]: https://github.com/Netflix/RxJava
  [19]: https://github.com/square/okhttp
  [20]: https://github.com/FasterXML/jackson
  [21]: https://bitbucket.org/qbusict/cupboard
  [22]: https://github.com/chrisjenx/Calligraphy
  [23]: https://github.com/junit-team/junit
  [24]: https://github.com/robolectric/robolectric
  [25]: https://github.com/mockito/mockito
  [26]: https://github.com/square/fest-android
  [27]: https://www.crittercism.com
  [28]: http://www.google.com/analytics
  [29]: https://www.jetbrains.com/idea/documentation/howto.html
  [30]: https://github.com/square/dagger-intellij-plugin
  [31]: https://code.google.com/p/lombok-intellij-plugin
  [32]: https://github.com/rzwitserloot/lombok