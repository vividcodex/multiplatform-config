package cn.vividcode.multiplatform.config.api

actual inline fun <reified T : Comparable<*>> config(default: T?): ConfigProperty<T> = DesktopConfigProperty(T::class, default)