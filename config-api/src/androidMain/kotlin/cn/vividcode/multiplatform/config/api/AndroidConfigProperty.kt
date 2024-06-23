package cn.vividcode.multiplatform.config.api

import android.content.Context
import kotlin.reflect.KClass

/**
 * AndroidConfigProperty
 */
class AndroidConfigProperty<T : Comparable<*>>(
	kClass: KClass<T>,
	default: T?
) : ConfigProperty<T>(kClass, default) {

	private companion object {

		private val sharedPreferences by lazy {
			ContextConfig.globalContext
				.getSharedPreferences(Configure.configFileName, Context.MODE_PRIVATE)
		}
	}

	override fun getValue(key: String): String? {
		return sharedPreferences.getString(key, null)
	}

	override fun setValue(key: String, value: String) {
		return sharedPreferences.edit().putString(key, value).apply()
	}
}