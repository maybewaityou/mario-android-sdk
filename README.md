##mario-android-sdk
>mario is used in react native project.

###installation
```
yarn add mario-android-sdk
or
npm install --save mario-android-sdk
```

###How to config

In the settings.gradle, add:

```
- include ':app'
+ include ':app', ':mario-android-sdk'

+ project(':mario-android-sdk').projectDir = new File(rootProject.projectDir, '../node_modules/mario-android-sdk/android/app')
```

In app module's build.gradle, add:

```
compile project(":mario-android-sdk")
```

###How to use
At MainApplication.java

```
@Override
protected List<ReactPackage> getPackages() {
    return Arrays.<ReactPackage>asList(
-            new MainReactPackage()
+            new MainReactPackage(),
+            new RegisterPackages()
    );
}
```

and

```
@Override
public void onCreate() {
    super.onCreate();

    FrameworkApplication.init(this);
}
```

so, you can use mario-android-sdk.

Happy hacking : )
