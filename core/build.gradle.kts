plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()
android {
    namespace = "com.example.core"
}
dependencies {

    implementation(platform("androidx.compose:compose-bom:2023.03.00"))

    compose()

    coroutines()

    hilt()

    retrofitAndOkHttp()

    paging()
}
