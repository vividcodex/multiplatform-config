package cn.vividcode.multiplatform.config.api

/**
 * android - config
 */
actual inline fun <reified T : Comparable<*>> config(configure: T?): ConfigProperty<T> = AndroidConfigProperty(T::class, configure)