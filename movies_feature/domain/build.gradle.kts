import deps.coroutines
import deps.hilt
import deps.retrofitAndOkHttp
import plugs.SharedLibraryGradlePlugin

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()
android {
    namespace = "com.example.domain"
}

dependencies {
hilt()



}
