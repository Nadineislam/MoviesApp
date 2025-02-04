import deps.compose
import deps.coroutines
import deps.hilt
import deps.paging
import deps.retrofitAndOkHttp
import deps.tests
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
tests()
    hilt()

    retrofitAndOkHttp()

    paging()
}
