import extension.addAppModuleDependencies

plugins {
    id(Plugins.ANDROID_APPLICATION_PLUGIN)
    id(Plugins.KOTLIN_ANDROID_PLUGIN)
    id(Plugins.KOTLIN_ANDROID_EXTENSIONS_PLUGIN)
    id(Plugins.KOTLIN_KAPT_PLUGIN)
}

android {
    compileSdkVersion(AndroidVersion.COMPILE_SDK_VERSION)
//    buildToolsVersion "30.0.3"

    defaultConfig {
        applicationId = AndroidVersion.APPLICATION_ID
        minSdkVersion(AndroidVersion.MIN_SDK_VERSION)
        targetSdkVersion(AndroidVersion.TARGET_SDK_VERSION)
        versionCode = AndroidVersion.VERSION_CODE
        versionName = AndroidVersion.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        com.android.build.api.variant.BuildConfigField("BASE_URL", AppConfig.APP_BASE_URL, null)

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = true
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packagingOptions {
        exclude("**/attach_hotspot_windows.dll")
        exclude("META-INF/licenses/**")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }

    android.buildFeatures.dataBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

     dynamicFeatures = mutableSetOf(
        Modules.DynamicFeature.NFS_LOGIN
//        Modules.DynamicFeature.HOME,
//        Modules.DynamicFeature.SEARCH,
//        Modules.DynamicFeature.DASHBOARD,
//        Modules.DynamicFeature.NOTIFICATION,
//        Modules.DynamicFeature.ACCOUNT
    )

//    testOptions {
//        unitTests.isIncludeAndroidResources = true
//    }

}


dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.AndroidLibrary.CORE))
//    implementation(project(Modules.AndroidLibrary.DOMAIN))
//    implementation(project(Modules.AndroidLibrary.DATA))

    addAppModuleDependencies()

    // Unit Tests
//    addUnitTestDependencies()
//    testImplementation(project(Modules.AndroidLibrary.TEST_UTILS))

    // Instrumentation Tests
//    addInstrumentationTestDependencies()
//    androidTestImplementation(project(Modules.AndroidLibrary.TEST_UTILS))
}
repositories {
    mavenCentral()
}