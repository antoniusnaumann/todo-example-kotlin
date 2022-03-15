val ktor_version: String by project

plugins {
    kotlin("jvm")
    id("application")
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
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation(kotlin("stdlib"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}