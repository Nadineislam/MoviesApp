import deps.compose
import deps.coroutines
import deps.hilt
import deps.paging
import deps.retrofitAndOkHttp
import plugs.SharedLibraryGradlePlugin

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()
android {
    namespace = "com.example.core"
}
dependencies {

    compose()

    coroutines()

    hilt()

    retrofitAndOkHttp()

    paging()
}
