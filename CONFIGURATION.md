There's things that need to be configured (such as app tokens and so on)

1. At [app/src/main/res/values/analytics.xml][3] change your Google Analytics `ga_trackingId` appropriately, or completely remove GA if you don't need it (see *removing GA section*)
2. At [app/src/main/res/values/tokens.xml][4] change `crittercism_app_id`, or completely remove it if not needed (see *removing Crittercism section*)
3. At [app/src/main/AndroidManifest.xml][5] change the `package` field how you need it
4. Now the toughest part - renaming the default `com.companyname.appname` java package
    1. At the project view, click on a gear icon and unselect *Compact empty middle packages*, this will allow us to rename the empty packages
    2. Now select any package root, `companyname`, for example. Highlight it and do `Refactor` -> `Rename` -> `Rename package`. This will rename the package root and all the underlying packages, imports and also do that for `test`source folder
    3. Click the gear icon and select *Hide empty middle packages* again

  [3]: app/src/main/res/values/analytics.xml
  [4]: app/src/main/res/values/tokens.xml
  [5]: app/src/main/AndroidManifest.xml