Android app skeleton
=======================================
## Continuous integration
* Travis CI [![Build Status](https://travis-ci.org/fs/android-base.png)](https://travis-ci.org/fs/android-base/pull_requests)
* Circle CI [![Build Status](https://circleci.com/gh/fs/android-base.png?style=shield&circle-token=c932b3e8650c436df970e9d1e9e06e8ef8fc9893)](https://circleci.com/gh/fs/android-base)
* Codecov [![codecov](https://codecov.io/gh/fs/android-base/branch/master/graph/badge.svg)](https://codecov.io/gh/fs/android-base)

## Prerequisites
* [Android Studio](https://developer.android.com/sdk/installing/studio.html) 3.x
* JDK 8
* Android SDK

## Updating secret keys
1. Decrypt file:

```bash
openssl aes-256-cbc -d -md sha256 -nosalt -a -pass pass:{KEY} -in secrets/keys.properties.crypted > temp.properties
```

2. Add/remove keys inside `temp.properties`
3. Encrypt temp.properties back

```bash
openssl aes-256-cbc -e -md sha256 -nosalt -a -pass pass:{KEY} -in temp.properties -out ./secrets/keys.properties.crypted

```

4. Clean up: `rm temp.properties`.

## What's included:
* [Staging and Production](https://github.com/fs/android-base/blob/master/app/build.gradle#L29-L38) build flavors with different package names ([read more](http://tools.android.com/tech-docs/new-build-system/user-guide#TOC-Product-flavors))
* *Android Lint* [configuration](https://github.com/fs/android-base/blob/master/app/build.gradle#L56-L61)
* *Travis CI* and *CircleCI* build [script1](https://github.com/fs/android-base/blob/master/.travis.yml) [script2](https://github.com/fs/android-base/blob/master/circle.yml):
    * Downloading an *Android SDK*
    * Building
    * Running *Android Lint*
    * Hook up your continuous deployment target in [`after_success`](https://github.com/fs/android-base/blob/master/.travis.yml#L40) for travis and in ['deployment'](https://github.com/fs/android-base/blob/master/circle.yml#L20) for CircleCi
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


### Deploying to Fabric
1. Increase app version & build number.
2. Commit changes.
3. Create git tag.
4. `git push && git push --tags`
5. Wait until https://circleci.com finish build.
6. Open crashlytics application on Android device
7. Find Android Base app, click on it and click "Update".
