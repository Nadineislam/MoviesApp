package deps


object Dependencies {
    const val CORE_KTX = "androidx.core:core-ktx:${DependencyVersions.CORE_KTX}"
    const val LIFECYCLE_VIEWMODEL_COMPOSE = "androidx.lifecycle:lifecycle-viewmodel-compose:${DependencyVersions.LIFECYCLE_VIEWMODEL_COMPOSE}"
    const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${DependencyVersions.LIFECYCLE_VIEWMODEL_KTX}"
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${DependencyVersions.ACTIVITY_COMPOSE}"

    const val COMPOSE_UI = "androidx.compose.ui:ui"
    const val COMPOSE_UI_GRAPHICS = "androidx.compose.ui:ui-graphics"
    const val COMPOSE_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val COMPOSE_MATERIAL3 = "androidx.compose.material3:material3:${DependencyVersions.COMPOSE_MATERIAL3}"
    const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling"
    const val COMPOSE_BOM_202210 = "androidx.compose:compose-bom:${DependencyVersions.COMPOSE_BOM_202210}"
    const val COMPOSE_BOM_202303 = "androidx.compose:compose-bom:${DependencyVersions.COMPOSE_BOM_202303}"
    const val COIL = "io.coil-kt:coil-compose:${DependencyVersions.COIL}"
    const val LIFECYCLE_RUNTIME_COMPOSE = "androidx.lifecycle:lifecycle-runtime-compose:${DependencyVersions.LIFECYCLE_RUNTIME_COMPOSE}"

    // Coroutines
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${DependencyVersions.COROUTINES_CORE}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependencyVersions.COROUTINES_ANDROID}"

    // Hilt
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${DependencyVersions.HILT_ANDROID}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${DependencyVersions.HILT_ANDROID}"
    const val HILT_COMPILER = "androidx.hilt:hilt-compiler:${DependencyVersions.HILT_COMPILER}"
    const val HILT_NAVIGATION_COMPOSE = "androidx.hilt:hilt-navigation-compose:${DependencyVersions.HILT_COMPILER}"

    // Retrofit and OkHttp
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${DependencyVersions.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${DependencyVersions.RETROFIT}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${DependencyVersions.OKHTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${DependencyVersions.OKHTTP}"

    // Paging
    const val PAGING_COMPOSE = "androidx.paging:paging-compose:${DependencyVersions.PAGING_COMPOSE}"

    // Testing
    const val JUNIT = "junit:junit:${DependencyVersions.JUNIT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${DependencyVersions.ESPRESSO}"
    const val MOCKITO = "org.mockito:mockito-core:${DependencyVersions.MOCKITO}"
    const val JUPITER_API = "org.junit.jupiter:junit-jupiter-api:${DependencyVersions.JUPITER_API}"
    const val JUPITER_ENGINE = "org.junit.jupiter:junit-jupiter-engine:${DependencyVersions.JUPITER_API}"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:${DependencyVersions.MOCKITO_INLINE}"
    const val ANDROID_TEST_JUNIT = "androidx.test.ext:junit:${DependencyVersions.ANDROID_TEST_JUNIT}"
    const val KOTLINX_COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${DependencyVersions.KOTLINX_COROUTINES_TEST}"

    const val CORE_TESTING = "androidx.arch.core:core-testing:${DependencyVersions.CORE_TESTING}"


    const val COMPOSE_UI_TEST_JUNIT4 = "androidx.compose.ui:ui-test-junit4"
    const val COMPOSE_UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest"

    const val CHUCKER_DEBUG = "com.github.chuckerteam.chucker:library:${DependencyVersions.CHUCKER}"
    const val CHUCKER_RELEASE = "com.github.chuckerteam.chucker:library-no-op:${DependencyVersions.CHUCKER}"
}