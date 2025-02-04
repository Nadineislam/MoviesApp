import deps.hilt
import deps.kotlinx
import deps.tests
import plugs.SharedLibraryGradlePlugin

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()
android {
    namespace = "com.example.domain"
}

dependencies {
    tests()
    hilt()
    kotlinx()
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.7.7")

}