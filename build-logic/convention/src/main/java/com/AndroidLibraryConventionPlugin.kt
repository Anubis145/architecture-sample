import com.android.build.gradle.LibraryExtension
import com.buildConfig.configureKotlinAndroid
import com.buildConfig.configureProductFlavors
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("kotlin-android")
            }

            with(extensions.getByType<LibraryExtension>()) {
                buildFeatures {
                    buildConfig = true
                }

                configureKotlinAndroid(this)
                configureProductFlavors()
            }
        }
    }
}