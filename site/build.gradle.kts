import com.varabyte.kobweb.gradle.application.extensions.AppBlock.LegacyRouteRedirectStrategy
import com.varabyte.kobweb.gradle.application.extensions.excludeTagsForDependency
import com.varabyte.kobweb.gradle.application.extensions.export
import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import kotlinx.html.script
import kotlin.io.path.Path
import kotlin.time.Duration

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
    kotlin("plugin.serialization") version "1.9.20"

}

group = "com.hcode.hgames"
version = "1.0-SNAPSHOT"

kobweb {
    version = "0.17.0"
    app {
        index {
            description.set("Powered by Kobweb")
        }
        legacyRouteRedirectStrategy.set(LegacyRouteRedirectStrategy.DISALLOW)
        export.timeout = Duration.parse("600s")

    }
}

kotlin {
    configAsKobwebApplication("hgames")

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)

        }

        jsMain.dependencies {
            implementation(compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation(libs.kobwebx.markdown)
            implementation("com.github.stevdza-san:KotlinBootstrap:0.1.0")
            implementation("io.ktor:ktor-client-core:2.3.8")
            implementation("io.ktor:ktor-client-json:2.3.8")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
        }
    }
}
