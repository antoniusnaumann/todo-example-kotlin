pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    
}
rootProject.name = "todo-example-kotlin"

include(":entities")
include(":server")
include(":client")

include(":client:compose-app")
include(":client:compose-app:android")
include(":client:compose-app:common")
include(":client:compose-app:desktop")
include(":client:swiftui-app")
