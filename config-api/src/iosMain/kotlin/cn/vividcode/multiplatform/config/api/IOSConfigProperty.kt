package cn.vividcode.multiplatform.config.api

import platform.Foundation.NSUserDefaults
import kotlin.reflect.KClass

/**
 * IOSConfigProperty
 */
class IOSConfigProperty<T : Comparable<*>>(
	kClass: KClass<T>,
	default: T?
) : ConfigProperty<T>(kClass, default) {
	
	override fun getValue(key: String): String? {
		return NSUserDefaults.standardUserDefaults.stringForKey(key)
	}
	
	override fun setValue(key: String, value: String) {
		NSUserDefaults.standardUserDefaults.setObject(value, key)
	}
}