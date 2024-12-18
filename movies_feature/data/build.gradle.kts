plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.example.data"

}

dependencies {

    coroutines()

    hilt()

    retrofitAndOkHttp()

    implementation(project(":movies_feature:domain"))

    Chucker()
}
