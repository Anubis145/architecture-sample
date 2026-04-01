import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class ComposeDestinationsConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                "implementation"(libs.findBundle("compose-destinations").get())
                "ksp"(libs.findLibrary("compose-destinations-ksp").get())
            }

            afterEvaluate {
                // set ksp destination for compose-destination the same as a namespace
                val androidExtension =
                    extensions.findByName("android") as? com.android.build.gradle.LibraryExtension
                        ?: return@afterEvaluate

                val namespace = androidExtension.namespace
                if (namespace != null) {
                    extensions.findByType(KspExtension::class.java)?.apply {
                        arg("compose-destinations.codeGenPackageName", namespace)
                    }
                } else {
                    error("Namespace must be defined in android block!")
                }
            }
        }
    }
}
