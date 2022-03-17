pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    
}
rootProject.name = "todo-example-kotlin"

include(":server")
include(":shared")
include(":client")

include(":shared:entities")

include(":client:compose-app")
include(":client:compose-app:android")
include(":client:compose-app:common")
include(":client:compose-app:desktop")
include(":client:swiftui-app")
include(":client:shared")
