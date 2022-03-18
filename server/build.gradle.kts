val ktorVersion: String by project
val serializationVersion: String by project

plugins {
    kotlin("jvm")
    id("application")
    kotlin("plugin.serialization") version "1.6.10"
}

group = "dev.antonius"
version = "0.0.0-alpha"

repositories {
    mavenCentral()
}

application {
    mainClass.set("dev.antonius.todo.server.ApplicationKt")
}

dependencies {
    // Ktor core and basic plugins
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")

    // Serialization
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

    // Logging
    implementation("ch.qos.logback:logback-classic:1.2.11")

    // Project
    implementation(project(":shared:entities"))

    // Standard Library
    implementation(kotlin("stdlib"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}