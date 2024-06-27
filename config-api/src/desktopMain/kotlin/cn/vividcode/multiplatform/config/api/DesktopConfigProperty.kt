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
		
		private val configureFile by lazy {
			val appName = if (Configure.appName.isNotBlank()) Configure.appName else error("appName 为空")
			val parent = System.getProperty("user.home") + "/Documents/${appName}Files/Config"
			File(parent, Configure.configFileName + ".xml")
		}
		
		private val properties by lazy {
			Properties().apply {
				if (configureFile.exists()) {
					configureFile.inputStream().use(::loadFromXML)
				} else {
					configureFile.apply {
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
		configureFile.outputStream().use {
			properties.storeToXML(it, null)
		}
	}
}