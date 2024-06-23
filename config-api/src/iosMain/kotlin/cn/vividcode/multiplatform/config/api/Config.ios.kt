package cn.vividcode.multiplatform.config.api

/**
 * ios - config
 */
actual inline fun <reified T : Comparable<*>> config(configure: T?): ConfigProperty<T> = IOSConfigProperty(T::class, configure)