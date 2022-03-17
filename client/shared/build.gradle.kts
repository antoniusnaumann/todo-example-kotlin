val ktorVersion: String by project

plugins {
    kotlin("multiplatform")
}

group = "dev.antonius"
version = "0.0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    /* js {
        browser()
    } */

    macosX64()
    macosArm64()
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))

                api("io.ktor:ktor-client-core:$ktorVersion")
                api("io.ktor:ktor-client-core:$ktorVersion")
                api("io.ktor:ktor-serialization:$ktorVersion")
                api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                api("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                api("io.ktor:ktor-client-serialization:$ktorVersion")
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
                api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.2")
                api("io.ktor:ktor-client-cio:$ktorVersion")

                api(project(":shared:entities"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}