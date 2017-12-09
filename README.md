Android app skeleton
=======================================
## Continuous integration
* Travis CI [![Build Status](https://travis-ci.org/fs/android-base.png)](https://travis-ci.org/fs/android-base/pull_requests)
* Circle CI [![Build Status](https://circleci.com/gh/fs/android-base.png?style=shield&circle-token=c932b3e8650c436df970e9d1e9e06e8ef8fc9893)](https://circleci.com/gh/fs/android-base)
* Nevercode CI [![Nevercode build status](https://app.nevercode.io/api/projects/af770237-5763-47d5-be12-14feee6adeb6/workflows/fc995e46-43fa-4edf-b261-6e11d97de632/status_badge.svg?branch=master&style=shields)](https://app.nevercode.io/#/project/af770237-5763-47d5-be12-14feee6adeb6/workflow/fc995e46-43fa-4edf-b261-6e11d97de632/latestBuild?branch=master)
* Codecov [![codecov](https://codecov.io/gh/fs/android-base/branch/master/graph/badge.svg)](https://codecov.io/gh/fs/android-base)

## Prerequisites
* [Android Studio](https://developer.android.com/sdk/installing/studio.html) 3.x
* JDK 8
* Android SDK

## What's included:
* [Staging and Production](https://github.com/fs/android-base/blob/master/app/build.gradle#L29-L38) build flavors with different package names ([read more](http://tools.android.com/tech-docs/new-build-system/user-guide#TOC-Product-flavors))
* *Android Lint* [configuration](https://github.com/fs/android-base/blob/master/app/build.gradle#L56-L61)
* *Travis CI* and *CircleCI* build [script1](https://github.com/fs/android-base/blob/master/.travis.yml) [script2](https://github.com/fs/android-base/blob/master/circle.yml):
    * Downloading an *Android SDK*
    * Building
    * Running *Android Lint*
    * Hook up your continuous deployment target in [`after_success`](https://github.com/fs/android-base/blob/master/.travis.yml#L40) for travis and in ['deployment'](https://github.com/fs/android-base/blob/master/circle.yml#L20) for CircleCi
* *Nevercode CI* setup. [Nevercode](https://nevercode.io/) (previously known as GreenhouseCI) created webhooks when you connected to GitHub and will build your app automatically for every git push
* Release build signing and naming configuration

## Setup
 1. Clone application as new project with original remote named "android-base"

    	git clone --depth 1 git://github.com/fs/android-base.git --origin android-base [MY-NEW-PROJECT]

    **Note: we use depth parameter here in order to not copy the history of changes in base project**

 2. Create your new repository on the GitHub and push master into it. Make sure master branch is tracking origin repo.

      cd [MY-NEW-PROJECT]
    	git remote add origin git@github.com:[MY-GITHUB-ACCOUNT]/[MY-NEW-PROJECT].git
    	git push -u origin master

 3. Import the project into your favourite IDE.
Just select the root `build.gradle` and your IDE will do the rest.

### Configuration
* Change your app's package by either [renaming the folder structure for Java sources](https://github.com/fs/android-base/tree/master/app/src/main/java/com/flatstack/android) or by just changing this [constant](https://github.com/fs/android-base/blob/master/app/build.gradle#L5) in `build.gradle`

# Codestyles
Project contains default codestyle scheme at `./idea/codestyleSettings.xml`. Make sure that you use default project scheme to avoid mess with codestyle with your collegues.
Go to the Settings (`cmd + ,`) -> Editor -> Code Style look on top of right panel and select `Project` from `Scheme` dropdown. Apply -> Ok.

### Making a release build
* Uncomment [these lines](https://github.com/fs/android-base/blob/master/app/build.gradle#L41-L48) and fill them up with your credentials

### ProGuard
Project already has proguard config for included libraries.
Maintain [proguard-rules.pro](https://github.com/fs/android-base/blob/master/app/proguard-rules.pro) updated when you add new libraries or play with reflection.

## Credits
Android app skeleton is maintained by [Adel Nizamutdinov](http://github.com/adelnizamutdinov) and [Ilya Eremin](http://github.com/ilyaeremin).
It was written by [Flatstack](http://www.flatstack.com) with the help of our
[contributors](http://github.com/fs/android-base/contributors)

[<img src="http://www.flatstack.com/logo.svg" width="100"/>](http://www.flatstack.com)
