import deps.coroutines
import deps.hilt
import deps.moviesFeaturePresentation
import deps.retrofitAndOkHttp
import deps.tests
import plugs.SharedLibraryGradlePlugin

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.example.movies_feature"
}

dependencies {

    coroutines()

    hilt()

    tests()

    retrofitAndOkHttp()

    moviesFeaturePresentation()
}
