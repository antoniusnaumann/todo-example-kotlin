package dev.antonius.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

actual abstract class PlatformViewModel {
    actual val defaultScope: CoroutineScope = MainScope()
}