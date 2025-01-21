package deps

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.chucker() {
    debugImplementation(Dependencies.CHUCKER_DEBUG)
    releaseImplementation(Dependencies.CHUCKER_RELEASE)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_UI_GRAPHICS)
    implementation(Dependencies.COMPOSE_PREVIEW)
    implementation(Dependencies.COMPOSE_MATERIAL3)
    implementation(Dependencies.LIFECYCLE_RUNTIME_COMPOSE)
    debugImplementation(Dependencies.COMPOSE_UI_TOOLING)
    implementation(Dependencies.COIL)
    platform(Dependencies.COMPOSE_BOM_202210)
    platform(Dependencies.COMPOSE_BOM_202303)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.COROUTINES_ANDROID)
}

fun DependencyHandler.moviesFeatureDomain() {
    moduleApi(project(":movies_feature:domain"))
}

fun DependencyHandler.core() {
    moduleImplementation(project(":core"))
}

fun DependencyHandler.moviesFeaturePresentation() {
    moduleApi(project(":movies_feature:presentation"))
}

fun DependencyHandler.moviesFeaturePresentationImpl() {
    moduleImplementation(project(":movies_feature:presentation"))
}

fun DependencyHandler.moviesFeatureDomainImpl() {
    moduleImplementation(project(":movies_feature:domain"))
}

fun DependencyHandler.moviesFeatureDataImpl() {
    moduleImplementation(project(":movies_feature:data"))
}


fun DependencyHandler.hilt() {
    implementation(Dependencies.HILT_ANDROID)
    kapt(Dependencies.HILT_ANDROID_COMPILER)
    kapt(Dependencies.HILT_COMPILER)
    implementation(Dependencies.HILT_NAVIGATION_COMPOSE)
}

fun DependencyHandler.retrofitAndOkHttp() {
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_GSON)
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_LOGGING_INTERCEPTOR)
}

fun DependencyHandler.paging() {
    implementation(Dependencies.PAGING_COMPOSE)
}

fun DependencyHandler.tests() {
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.ESPRESSO)
    androidTestImplementation(Dependencies.CORE_TESTING)
    androidTestImplementation(Dependencies.COMPOSE_UI_TEST_JUNIT4)
    androidTestImplementation(Dependencies.COMPOSE_UI_TEST_MANIFEST)
    testImplementation(Dependencies.MOCKITO)
    testImplementation(Dependencies.JUPITER_API)
    testRuntimeOnly(Dependencies.JUPITER_ENGINE)
    testImplementation(Dependencies.MOCKITO_INLINE)
    androidTestImplementation(Dependencies.ANDROID_TEST_JUNIT)
    testImplementation(Dependencies.KOTLINX_COROUTINES_TEST)
}

fun DependencyHandler.androidxLifecycle() {
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.ACTIVITY_COMPOSE)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_COMPOSE)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_KTX)
}