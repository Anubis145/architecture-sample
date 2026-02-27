import com.android.build.api.dsl.ApplicationExtension
import com.buildConfig.Constants
import com.buildConfig.configureAppSuffix
import com.buildConfig.configureKotlinAndroid
import com.buildConfig.configureProductFlavors
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.gms.google-services")
            }

            extensions.configure<ApplicationExtension> {

                defaultConfig.targetSdk = Constants.TARGET_SDK
                defaultConfig.versionCode = 1
                defaultConfig.versionName = "ArchTest___1"

                bundle {
                    language {
                        enableSplit = false
                    }
                }

                configureKotlinAndroid(this)
                configureProductFlavors()
                configureAppSuffix()
            }
        }
    }
}