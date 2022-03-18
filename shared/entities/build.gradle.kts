val serializationVersion: String by project

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.6.10"
    java
}

group = "dev.antonius"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
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
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        // val jsMain by getting
        val jvmMain by getting
    }
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}