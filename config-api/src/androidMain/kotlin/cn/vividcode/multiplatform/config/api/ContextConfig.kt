package cn.vividcode.multiplatform.config.api

import android.app.Application
import android.content.Context

/**
 * ContextConfig
 */
object ContextConfig {
	
	private var application: Application? = null
	
	fun init(application: Application) {
		this.application = application
	}
	
	/**
	 * global context
	 */
	val globalContext: Context
		get() = this.application?.applicationContext ?: error("请配置application")
}