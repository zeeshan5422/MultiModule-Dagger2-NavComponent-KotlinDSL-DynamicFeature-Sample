import extension.addCoreModuleDependencies
import com.android.build.api.variant.BuildConfigField

plugins {
    id(Plugins.ANDROID_LIBRARY_PLUGIN)
    id(Plugins.KOTLIN_ANDROID_PLUGIN)
    id(Plugins.KOTLIN_ANDROID_EXTENSIONS_PLUGIN)
    id(Plugins.KOTLIN_KAPT_PLUGIN)
}
apply {
    plugin("kotlin-android")
}

android {

    compileSdkVersion(AndroidVersion.COMPILE_SDK_VERSION)
    defaultConfig {
        minSdkVersion(AndroidVersion.MIN_SDK_VERSION)
        targetSdkVersion(AndroidVersion.TARGET_SDK_VERSION)
        versionCode = AndroidVersion.VERSION_CODE
        versionName = AndroidVersion.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        BuildConfigField("BASE_URL",AppConfig.APP_BASE_URL,null)

    }

    buildTypes {
        getByName("debug") {

            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("release") {
            isMinifyEnabled = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    android.buildFeatures.dataBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

//    implementation(project(Modules.AndroidLibrary.DOMAIN))
//    implementation(project(Modules.AndroidLibrary.DATA))

    addCoreModuleDependencies()

    // Glide
    implementation(Deps.GLIDE)
    kapt(Deps.GLIDE_COMPILER)

    // Gson
    implementation(Deps.GSON)

    // Okhttp3
    implementation(Deps.OK_HTTP3)
    implementation(Deps.OK_HTTP3_LOGGING)

    // Retrofit
    implementation(Deps.RETROFIT)
    implementation(Deps.RETROFIT_GSON_CONVERTER)
    implementation(Deps.RETROFIT_RX_JAVA3_ADAPTER)
    // change base url runtime
//    implementation(Deps.RETROFIT_URL_MANAGER)

    // Room
    implementation(Deps.ROOM_RUNTIME)
    // For Kotlin use kapt instead of annotationProcessor
    kapt(Deps.ROOM_COMPILER)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(Deps.ROOM_KTX)

    // Chucker
//    implementation(Deps.CHUCKER_DEBUG)
//    implementation(Deps.CHUCKER_RELEASE)

    implementation( "com.agrawalsuneet.androidlibs:squareloaderspack:0.5")

}
repositories {
    mavenCentral()
}