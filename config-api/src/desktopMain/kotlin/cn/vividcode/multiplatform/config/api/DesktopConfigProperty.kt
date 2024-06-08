package cn.vividcode.multiplatform.config.api

import java.io.File
import java.util.*
import kotlin.reflect.KClass

/**
 * DesktopConfigProperty
 */
class DesktopConfigProperty<T : Comparable<*>>(
	kClass: KClass<T>,
	default: T?
) : ConfigProperty<T>(kClass, default) {
	
	private companion object {
		
		private val configFile by lazy {
			val appName = if (Config.appName.isNotBlank()) Config.appName else error("appName 为空")
			val parent = System.getProperty("user.home") + "/Documents/${appName}Files/Config"
			File(parent, Config.configFileName + ".xml")
		}
		
		private val properties by lazy {
			Properties().apply {
				if (configFile.exists()) {
					configFile.inputStream().use {
						loadFromXML(it)
					}
				} else {
					configFile.apply {
						if (!parentFile.exists()) {
							parentFile.mkdirs()
						}
						createNewFile()
					}
				}
			}
		}
	}
	
	override fun getValue(key: String): String? {
		return properties.getProperty(key)
	}
	
	override fun setValue(key: String, value: String) {
		properties.setProperty(key, value)
		configFile.outputStream().use {
			properties.storeToXML(it, null)
		}
	}
}