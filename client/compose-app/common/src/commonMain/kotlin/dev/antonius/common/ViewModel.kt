package dev.antonius.common

import kotlinx.coroutines.CoroutineScope

expect abstract class PlatformViewModel() {
    val defaultScope: CoroutineScope
}