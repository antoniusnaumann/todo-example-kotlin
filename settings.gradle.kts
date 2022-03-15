pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    
}
rootProject.name = "todo-example-kotlin"

include(":compose-app")
include(":compose-app:android")
include(":compose-app:common")
include(":compose-app:desktop")
include(":entities")
include(":server")
