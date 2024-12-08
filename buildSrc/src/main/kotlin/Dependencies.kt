import org.gradle.api.artifacts.dsl.DependencyHandler


object Dependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleViewModelCompose}"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModelKtx}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"

    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial3 = "androidx.compose.material3:material3"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeBom202210 = "androidx.compose:compose-bom:${Versions.composeBom202210}"
    const val composeBom202303 = "androidx.compose:compose-bom:${Versions.composeBom202303}"
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coil}"
    const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycleRuntimeCompose}"

    // Coroutines
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"

    // Hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroid}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroid}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltCompiler}"

    // Retrofit and OkHttp
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    // Paging
    const val pagingCompose = "androidx.paging:paging-compose:${Versions.pagingCompose}"

    // Testing
    const val junit = "junit:junit:${Versions.junit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    const val jupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.jupiterApi}"
    const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.jupiterApi}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    const val androidTestJUnit = "androidx.test.ext:junit:${Versions.androidTestJUnit}"
    const val kotlinxCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinxCoroutinesTest}"

    const val archCoreTesting = "androidx.arch.core:core-testing:2.2.0"


    const val composeUiTestJUnit4 = "androidx.compose.ui:ui-test-junit4"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"

    const val chuckerDebug = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
    const val chuckerRelease = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"

}
fun DependencyHandler.Chucker() {
    debugImplementation(Dependencies.chuckerDebug)
    releaseImplementation(Dependencies.chuckerRelease)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.lifecycleRuntimeCompose)
    debugImplementation(Dependencies.composeUiTooling)
    implementation(Dependencies.coilCompose)
    platform(Dependencies.composeBom202210)
    platform(Dependencies.composeBom202303)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)
    kapt(Dependencies.hiltCompiler)
    implementation(Dependencies.hiltNavigationCompose)
}

fun DependencyHandler.retrofitAndOkHttp() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitConverterGson)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.okhttpLoggingInterceptor)
}

fun DependencyHandler.paging() {
    implementation(Dependencies.pagingCompose)
}

fun DependencyHandler.tests() {
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(Dependencies.archCoreTesting)
    androidTestImplementation(Dependencies.composeUiTestJUnit4)
    androidTestImplementation(Dependencies.composeUiTestManifest)
    testImplementation(Dependencies.mockitoCore)
    testImplementation(Dependencies.jupiterApi)
    testRuntimeOnly(Dependencies.jupiterEngine)
    testImplementation(Dependencies.mockitoInline)
    androidTestImplementation(Dependencies.androidTestJUnit)
    testImplementation(Dependencies.kotlinxCoroutinesTest)
}
fun DependencyHandler.androidxLifecycle() {
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.lifecycleViewModelCompose)
    implementation(Dependencies.lifecycleViewModelKtx)
}