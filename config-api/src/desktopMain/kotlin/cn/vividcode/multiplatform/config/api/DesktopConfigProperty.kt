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
			val parent = System.getProperty("user.home") + "/Documents/${Config.appName}Files/Config"
			File(parent, Config.name + ".xml")
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