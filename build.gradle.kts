// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlin_version by extra("1.4.31")
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Plugins.CLASSPATH_GRADLE) // Android Build Tool dependency
        classpath(kotlin("gradle-plugin", version = PluginVersion.KOTLIN_VERSION)) // kitlin dependency
//        classpath(Plugins.CLASSPATH_DAGGER_HILT)  // Dagger Hilt DI dependency
//        classpath(Plugins.CLASSPATH_KTLINT)
        classpath(Plugins.CLASSPATH_NAV_SAFE_ARGS) // Jetpack Navigation Component SafeArgs dependency

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}