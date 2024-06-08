package cn.vividcode.multiplatform.config.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

/**
 * config info
 */
sealed interface Config {
	
	companion object {
		
		val aesKey by lazy { ConfigImpl.aesKey }
		
		val aesIv by lazy { ConfigImpl.aesIv }
		
		val appName by lazy { ConfigImpl.appName }
		
		val configFileName by lazy { ConfigImpl.configFileName }
	}
	
	/**
	 * AES KEY
	 */
	var aesKey: String
	
	/**
	 * AES Iv
	 */
	var aesIv: String
	
	/**
	 * app name
	 */
	var appName: String
	
	/**
	 * config file name
	 */
	var configFileName: String
}

private data object ConfigImpl : Config {
	
	override var aesKey = "A6j3akipAQaiYeIj"
	
	override var aesIv = "3pUjGpdtTKXMTysmE2UHzwjnRoXzXlO1esLrvdL5BmKk0J1q9lThStZp4HAX9QUn"
	
	override var appName = ""
	
	override var configFileName = "config"
}

/**
 * config
 */
expect inline fun <reified T : Comparable<*>> config(default: T? = null): ConfigProperty<T>

@Composable
fun config(config: Config.() -> Unit) {
	LaunchedEffect(Unit) {
		ConfigImpl.apply(config)
	}
}