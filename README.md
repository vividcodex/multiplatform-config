# 基于 Kotlin Multiplatform 的持久化配置框架

## 仓库版本

cn.vividcode.multiplatform:config-api:1.0.1

## 支持数据类型

> String, Boolean, Short, Int, Long, Float, Double

## 使用教程

```kotlin

// 在启动的时候配置config的配置项，此配置项不会重复执行
@Composable
fun App() { 
    configure {
        this.appName = "<必填项>"
        this.aesKey = "<请替换成自己的aesKey，随机的字符串>"
        this.aesIv = "<请替换成自己的aesIv，随机的字符串>"
        this.configFileName = "<配置文件名称，默认：config>"
    }
}

// 如何使用？查看以下代码
object TestConfig {
	
	/**
     * 指定默认值，数据类型和默认值类型一致
	 */
	var stringConfig1 by config(default = "hello")
	
	/**
     * 指定默认类型，String默认值为: ""，Boolean为: false，Number为: 0
	 */
	var stringConfig2: String by config()
}

```