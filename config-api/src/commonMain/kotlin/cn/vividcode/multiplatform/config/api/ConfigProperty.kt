package cn.vividcode.multiplatform.config.api

import cn.vividcode.multiplatform.config.api.expends.decrypt
import cn.vividcode.multiplatform.config.api.expends.encrypt
import cn.vividcode.multiplatform.config.api.expends.lowercaseWithUnderline
import cn.vividcode.multiplatform.config.api.expends.sha256
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * config property
 */
abstract class ConfigProperty<T : Comparable<*>>(
	private val kClass: KClass<T>,
	private val default: T?
) : ReadWriteProperty<Any?, T> {
	
	private var shaKey: String? = null
	private var cacheValue: T? = null
	
	private companion object {
		
		private val defaultValueMap = mapOf(
			String::class to "",
			Short::class to 0,
			Int::class to 0,
			Long::class to 0L,
			Float::class to 0F,
			Double::class to 0.0,
			Boolean::class to false
		)
	}
	
	override fun getValue(thisRef: Any?, property: KProperty<*>): T {
		val shaKey = getSHAKey(thisRef, property)
		return if (cacheValue == null) {
			(getValue(shaKey)?.decrypt?.cast ?: default ?: kClass.default).also {
				this.cacheValue = it
			}
		} else this.cacheValue!!
	}
	
	override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
		val shaKey = getSHAKey(thisRef, property)
		if (this.cacheValue != value) {
			this.cacheValue = value
			setValue(shaKey, value.toString().encrypt)
		}
	}
	
	protected abstract fun getValue(key: String): String?
	
	protected abstract fun setValue(key: String, value: String)
	
	private fun getSHAKey(thisRef: Any?, property: KProperty<*>): String {
		return this.shaKey ?: let {
			buildString {
				if (thisRef != null && thisRef::class.simpleName != null) {
					append(thisRef::class.simpleName!!.lowercaseWithUnderline())
				}
				append('_')
				append(property.name.lowercase())
			}.sha256.also {
				this.shaKey = it
			}
		}
	}
	
	@Suppress("UNCHECKED_CAST")
	private val String.cast: T
		get() = when (kClass) {
			String::class -> this
			Short::class -> this.toShort()
			Int::class -> this.toInt()
			Long::class -> this.toLong()
			Float::class -> this.toFloat()
			Double::class -> this.toDouble()
			Boolean::class -> this.toBooleanStrict()
			else -> error("不支持的类型 ${kClass.simpleName}")
		} as T
	
	@Suppress("UNCHECKED_CAST")
	private val KClass<T>.default: T by lazy {
		defaultValueMap[kClass] as? T ?: error("不支持的类型 ${kClass.simpleName}")
	}
}