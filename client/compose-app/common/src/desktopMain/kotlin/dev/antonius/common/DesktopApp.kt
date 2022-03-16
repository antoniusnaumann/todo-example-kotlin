package dev.antonius.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable

actual val localhost: String = "127.0.0.1"

@Preview
@Composable
fun AppPreview() {
    App()
}