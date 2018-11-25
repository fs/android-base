Android app skeleton
=======================================
## Continuous integration
* Travis CI [![Build Status](https://travis-ci.org/fs/android-base.png)](https://travis-ci.org/fs/android-base/pull_requests)
* Circle CI [![Build Status](https://circleci.com/gh/fs/android-base.png?style=shield&circle-token=c932b3e8650c436df970e9d1e9e06e8ef8fc9893)](https://circleci.com/gh/fs/android-base)
* Nevercode CI [![Nevercode build status](https://app.nevercode.io/api/projects/af770237-5763-47d5-be12-14feee6adeb6/workflows/fc995e46-43fa-4edf-b261-6e11d97de632/status_badge.svg?branch=master&style=shields)](https://app.nevercode.io/#/project/af770237-5763-47d5-be12-14feee6adeb6/workflow/fc995e46-43fa-4edf-b261-6e11d97de632/latestBuild?branch=master)
* Codecov [![codecov](https://codecov.io/gh/fs/android-base/branch/master/graph/badge.svg)](https://codecov.io/gh/fs/android-base)

## Starting from base project
1. `git clone --depth 1 git://github.com/fs/android-base.git --origin android-base [NEW-PROJECT-NAME]`
2. `cd [NEW-PROJECT-NAME]`
3. `git remote add origin https://github.com/[NEW-PROJECT-GITHUB-ACCOUNT]/[NEW-PROJECT-NAME].git`
4. `git push -u origin master`
5. Update `APPLICATION_ID` in `app/build.gradle`.
6. Rename package under `app/src/main/java`.
7. Remove current and Credits sections from `README.MD`.

## Setup
1. `git clone your_project_url`
1. Download latest Android Studio from https://developer.android.com/studio/index.html
2. Follow Android Studio installation instruction.
3. Download and install latest JDK8 http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html.
4. Open Android Studio - Open Existing Android Project - find folder with project and click `OK`
5. Wait a while. Follow Android Studio instructions to install missing items.
6. Press `cmd + shift + a` and type `AVD Manager` and press Enter.
10. Press `Create Virtual Device...` button.
11. Select `Nexus 5X`
12. Select latest API level (in case if latest is not available then click `Download` and wait, it's going to take a while).
13. Click `Next`
14. Click `Finish`


## Running on device
[0. If you use real device: enable USB debugging and plugin device to your computer]
1. Press `cmd + shift + a`, type `run app` and press Enter.
2. Select device to run with

## Tests
[to be added]

## ProGuard
When you add new library or check out its Proguard section and add rules to `proguard-rules.pro`.
When you add code which uses reflection add rules to `proguard-rules.pro`.

## Credits
Android app skeleton is maintained by [Adel Nizamutdinov](http://github.com/adelnizamutdinov) and [Ilya Eremin](http://github.com/ilyaeremin).
It was written by [Flatstack](http://www.flatstack.com) with the help of our
[contributors](http://github.com/fs/android-base/contributors)

[<img src="http://www.flatstack.com/logo.svg" width="100"/>](http://www.flatstack.com)
