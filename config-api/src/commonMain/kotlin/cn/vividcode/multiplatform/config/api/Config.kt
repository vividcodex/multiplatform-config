package cn.vividcode.multiplatform.config.api

/**
 * config
 */
expect inline fun <reified T : Comparable<*>> config(default: T? = null): ConfigProperty<T>