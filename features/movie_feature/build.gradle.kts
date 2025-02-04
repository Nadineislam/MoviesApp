import deps.compose
import deps.core
import deps.coroutines
import deps.dataModule
import deps.domainModule
import deps.hilt
import deps.kotlinx
import deps.presentationModule
import deps.retrofitAndOkHttp
import deps.testImplementation
import plugs.SharedLibraryGradlePlugin

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.example.movie_feature"
}

dependencies {

    coroutines()

    hilt()

    compose()

    retrofitAndOkHttp()
    core()

    dataModule()
    domainModule()
    presentationModule()
    kotlinx()
    testImplementation("junit:junit:4.13.2")
    testImplementation ("org.mockito:mockito-junit-jupiter:5.4.0")
    testImplementation ("net.bytebuddy:byte-buddy:1.15.11")  // Add Byte Buddy dependency here
    testImplementation ("io.mockk:mockk:1.13.3")  // Add MockK dependency

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.4")
    testImplementation("org.mockito:mockito-core:5.15.2")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation ("org.mockito:mockito-android:5.15.2")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:5.4.0")  // Latest version
    testImplementation ("org.powermock:powermock-api-mockito2:2.0.9") // PowerMock for Mockito 2.x
    testImplementation ("org.powermock:powermock-module-junit4:2.0.9")

    // Replace with the latest version

    // Android Test Dependencies
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.compose.ui:ui-test-manifest:1.7.7")

    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.7.7")

}