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

    api(project(":movies_feature:presentation"))

}
