import deps.compose
import deps.domainModule
import deps.hilt
import deps.tests
import plugs.SharedLibraryGradlePlugin

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()
android {
    namespace = "com.example.presentation"
}

dependencies {
 compose()
    hilt()
    tests()
    domainModule()
}