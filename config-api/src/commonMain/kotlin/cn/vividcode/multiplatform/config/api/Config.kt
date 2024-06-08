package cn.vividcode.multiplatform.config.api

/**
 * config info
 */
object Config {
	
	/**
	 * AES KEY
	 */
	var aesKey = "A6j3akipAQaiYeIj"
	
	/**
	 * AES Iv
	 */
	var aesIv = "3pUjGpdtTKXMTysmE2UHzwjnRoXzXlO1esLrvdL5BmKk0J1q9lThStZp4HAX9QUn"
	
	/**
	 * app name
	 */
	var appName = ""
	
	/**
	 * name
	 */
	var name = "config"
}

/**
 * config
 */
expect inline fun <reified T : Comparable<*>> config(default: T? = null): ConfigProperty<T>