package cn.vividcode.multiplatform.config.sample

import App
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
	Window(
		onCloseRequest = ::exitApplication,
		title = "vividcode-multiplatform-config",
	) {
		App()
	}
}