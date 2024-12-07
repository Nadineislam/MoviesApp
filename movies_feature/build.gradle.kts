plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.DAGGER_HILT)
}
hilt {
    enableAggregatingTask = false
}
android {
    namespace = "com.example.movies_feature"
    compileSdk = BuildConfig.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = BuildConfig.MIN_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        //consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    coroutines()

    hilt()

    tests()

    retrofitAndOkHttp()

    api(project(":movies_feature:presentation"))

}
