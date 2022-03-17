package dev.antonius.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

actual abstract class PlatformViewModel: ViewModel() {
    actual val defaultScope get() = viewModelScope
}