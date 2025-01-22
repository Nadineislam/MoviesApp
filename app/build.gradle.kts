import deps.chucker
import deps.compose
import deps.hilt
import deps.moviesFeatureDataImpl
import deps.moviesFeatureDomainImpl
import deps.moviesFeaturePresentationImpl
import deps.retrofitAndOkHttp

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.DAGGER_HILT)
}
hilt {
    enableAggregatingTask = false
}
android {
    namespace = BuildConfig.APP_ID
    compileSdk = BuildConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = BuildConfig.APP_ID
        minSdk = BuildConfig.MIN_SDK_VERSION
        targetSdk = BuildConfig.TARGET_SDK_VERSION
        versionCode = BuildConfig.VERSION_CODE
        versionName = BuildConfig.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", "\"15bfb3d770d73513c71ab9f787cbe27b\"")
    }

    buildTypes {
        getByName(BuildTypes.RELEASE) {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isMinifyEnabled = Build.Release.isMinifyEnabled
            enableUnitTestCoverage = Build.Release.enableUnitTestCoverage
            isDebuggable = Build.Release.isDebuggable
        }
        getByName(BuildTypes.DEBUG){
            isMinifyEnabled = Build.Debug.isMinifyEnabled
            enableUnitTestCoverage = Build.Debug.enableUnitTestCoverage
            isDebuggable = Build.Debug.isDebuggable
            versionNameSuffix = Build.Debug.versionNameSuffix
            applicationIdSuffix = Build.Debug.applicationIdSuffix
        }
        create(BuildTypes.RELEASE_EXTERNAL_QA){
            isMinifyEnabled = Build.ReleaseExternalQa.isMinifyEnabled
            enableUnitTestCoverage = Build.ReleaseExternalQa.enableUnitTestCoverage
            isDebuggable = Build.ReleaseExternalQa.isDebuggable
            versionNameSuffix = Build.ReleaseExternalQa.versionNameSuffix
            applicationIdSuffix = Build.ReleaseExternalQa.applicationIdSuffix
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    compose()

    hilt()

    retrofitAndOkHttp()

    moviesFeatureDataImpl()
    moviesFeatureDomainImpl()
    moviesFeaturePresentationImpl()

    chucker()

}
