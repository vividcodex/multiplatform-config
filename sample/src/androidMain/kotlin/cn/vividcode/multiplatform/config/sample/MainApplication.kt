package cn.vividcode.multiplatform.config.sample

import android.app.Application
import cn.vividcode.multiplatform.config.api.ContextConfig

/**
 * 项目：vividcode-multiplatform-config
 *
 * 作者：li-jia-wei
 *
 * 创建：2024/6/9 上午2:13
 *
 * 介绍：MainApplication
 */
class MainApplication : Application() {
	
	override fun onCreate() {
		super.onCreate()
		ContextConfig.init(this)
	}
}