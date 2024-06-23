package cn.vividcode.multiplatform.config.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

/**
 * 参数配置
 */
sealed interface Configure {
	
	companion object {
		
		val aesKey by lazy { ConfigureImpl.aesKey }
		
		val aesIv by lazy { ConfigureImpl.aesIv }
		
		val appName by lazy { ConfigureImpl.appName }
		
		val configFileName by lazy { ConfigureImpl.configFileName }
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

private data object ConfigureImpl : Configure {
	
	override var aesKey = "A6j3akipAQaiYeIj"
	
	override var aesIv = "3pUjGpdtTKXMTysmE2UHzwjnRoXzXlO1esLrvdL5BmKk0J1q9lThStZp4HAX9QUn"
	
	override var appName = ""
	
	override var configFileName = "config"
}

private var isConfigure = false

/**
 * configure
 */
fun configure(configure: Configure.() -> Unit) {
	if (!isConfigure) {
		isConfigure = true
		ConfigureImpl.apply(configure)
	}
}