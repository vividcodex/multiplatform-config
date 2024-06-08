package cn.vividcode.multiplatform.config.api

import android.app.Application
import android.content.Context

/**
 * ContextConfig
 */
object ContextConfig {
	
	var application: Application? = null
	
	/**
	 * global context
	 */
	val globalContext: Context
		get() = this.application?.applicationContext ?: error("请配置application")
}