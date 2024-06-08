package cn.vividcode.multiplatform.config.sample

import android.app.Application
import cn.vividcode.multiplatform.config.api.ContextConfig

/**
 * MainApplication
 */
class MainApplication : Application() {
	
	override fun onCreate() {
		super.onCreate()
		ContextConfig.init(this)
	}
}