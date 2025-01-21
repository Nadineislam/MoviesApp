package plugs

import BuildConfig
import BuildPlugins
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

class SharedLibraryGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.addPluginConfigurations()
        project.addAndroidConfigurations()
        project.applyKotlinOptions()
    }

    private fun Project.addPluginConfigurations() {
        plugins.apply(BuildPlugins.KOTLIN_ANDROID)
        plugins.apply(BuildPlugins.KOTLIN_KAPT)
        plugins.apply(BuildPlugins.DAGGER_HILT)
    }

    private fun Project.addAndroidConfigurations() {
        extensions.getByType(LibraryExtension::class.java).apply {
            compileSdk = BuildConfig.COMPILE_SDK_VERSION
            defaultConfig {
                minSdk = BuildConfig.MIN_SDK_VERSION
                testInstrumentationRunner = BuildConfig.TEST_INSTRUMENTATION_RUNNER
            }
            composeOptions {
                kotlinCompilerExtensionVersion = "1.4.3"
            }
            buildFeatures {
                compose = true
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
        }
    }

    private fun Project.applyKotlinOptions() {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }
    }

}