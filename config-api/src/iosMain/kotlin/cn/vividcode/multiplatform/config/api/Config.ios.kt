package cn.vividcode.multiplatform.config.api

/**
 * ios - config
 */
actual inline fun <reified T : Comparable<*>> config(default: T?): ConfigProperty<T> = IOSConfigProperty(T::class, default)