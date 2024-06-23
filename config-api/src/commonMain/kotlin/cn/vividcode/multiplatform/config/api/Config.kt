package cn.vividcode.multiplatform.config.api

/**
 * config
 */
expect inline fun <reified T : Comparable<*>> config(configure: T? = null): ConfigProperty<T>