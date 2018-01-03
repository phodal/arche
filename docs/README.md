创建原生 Android 项目
---

使用 Android Studio 创建项目


RN
---

```
npm install -g create-react-native-app


create-react-native-app RNArche

cd RNArche
npm start
```

Inject

```
npm run inject
```

Q & A
---

```
libgnustl_shared.so" is 32-bit instead of 64-bit
```


```

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "arche.phodal.com.arche"
        minSdkVersion 16
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary= true
        ndk {
            abiFilters "armeabi-v7a", "x86"
        }
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}
```

###  permission denied for window type 2003

```

```

Bundle files
---


```
cp ./RNArche/android/app/build/intermediates/assets/release/index.android.bundle ./app/src/main/assets/index.android.bundle
```

WebView
---

```
npm install -g cordova ionic

ionic start archeWebview sidemenu

yarn build
```

问题：

```
java.lang.ClassCastException: android.app.Application cannot be cast to arche.phodal.com.arche.ArcheApplication
```



```
    <application
        android:name=".ArcheApplication"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
```
