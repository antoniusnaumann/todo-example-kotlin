plugins {
    kotlin("multiplatform")
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
    js {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jsMain by getting
        val jvmMain by getting
    }
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}