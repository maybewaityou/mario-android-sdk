##mario-android-sdk
>mario is used in react native project.

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

so, you can use mario-android-sdk.

Happy hacking : )
