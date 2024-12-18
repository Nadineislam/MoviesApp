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

    api(project(":movies_feature:domain"))

    implementation(project(":core"))
}
