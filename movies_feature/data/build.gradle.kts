import deps.chucker
import deps.coroutines
import deps.dataModule
import deps.hilt
import deps.moviesFeatureDomain
import deps.retrofitAndOkHttp
import plugs.SharedLibraryGradlePlugin

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.example.data"

}

dependencies {
hilt()

}
