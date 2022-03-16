package dev.antonius.todo.swiftui

// import platform.AppKit

actual class Platform actual constructor() {
    actual val platform: String = "macOS" // UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}