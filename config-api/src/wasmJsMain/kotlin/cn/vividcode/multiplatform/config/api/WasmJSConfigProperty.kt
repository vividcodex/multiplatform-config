package cn.vividcode.multiplatform.config.api

import kotlinx.browser.sessionStorage
import kotlin.reflect.KClass

/**
 * WasmJSConfigProperty
 */
class WasmJSConfigProperty<T : Comparable<*>>(
	kClass: KClass<T>,
	default: T?
) : ConfigProperty<T>(kClass, default) {
	
	override fun getValue(key: String): String? {
		return sessionStorage.getItem(key)
	}
	
	override fun setValue(key: String, value: String) {
		sessionStorage.setItem(key, value)
	}
}