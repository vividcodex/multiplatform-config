package cn.vividcode.multiplatform.config.api

actual inline fun <reified T : Comparable<*>> config(configure: T?): ConfigProperty<T> = DesktopConfigProperty(T::class, configure)