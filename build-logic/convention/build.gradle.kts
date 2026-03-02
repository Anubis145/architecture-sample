plugins {
    `kotlin-dsl`
}


dependencies {
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.compose.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}