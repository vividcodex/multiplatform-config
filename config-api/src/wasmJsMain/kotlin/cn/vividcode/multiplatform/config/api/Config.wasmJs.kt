package cn.vividcode.multiplatform.config.api

/**
 * wasmJS - config
 */
actual inline fun <reified T : Comparable<*>> config(default: T?): ConfigProperty<T> = WasmJSConfigProperty(T::class, default)