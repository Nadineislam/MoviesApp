plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()
android {
    namespace = "com.example.domain"
}

dependencies {

    coroutines()

    hilt()

    retrofitAndOkHttp()


}
