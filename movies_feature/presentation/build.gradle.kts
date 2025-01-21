import deps.androidxLifecycle
import deps.compose
import deps.core
import deps.coroutines
import deps.hilt
import deps.moviesFeatureDomain
import deps.paging
import deps.retrofitAndOkHttp
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

    coroutines()

    hilt()

    retrofitAndOkHttp()

    paging()

    androidxLifecycle()

    moviesFeatureDomain()

    core()
}
