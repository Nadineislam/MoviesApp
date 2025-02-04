import deps.chucker
import deps.domainModule
import deps.hilt
import deps.retrofitAndOkHttp
import deps.tests
import plugs.SharedLibraryGradlePlugin

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()
android {
    namespace = "com.example.core"
    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", "\"15bfb3d770d73513c71ab9f787cbe27b\"")
    }
}
android {
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    retrofitAndOkHttp()
    hilt()
    tests()
    chucker()
    domainModule()
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.7.7")

}