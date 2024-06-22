import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
	alias(libs.plugins.kotlin.multiplatform)
	alias(libs.plugins.android.application)
	alias(libs.plugins.jetbrains.compose)
	alias(libs.plugins.compose.compiler)
}

val configVersion = property("vividcode.config.version")!!.toString()

kotlin {
	@OptIn(ExperimentalWasmDsl::class)
	wasmJs {
		moduleName = "sampleApp"
		browser {
			commonWebpackConfig {
				outputFileName = "sampleApp.js"
				devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
					static = (static ?: mutableListOf()).apply {
						add(project.projectDir.path)
					}
				}
			}
		}
		binaries.executable()
	}
	
	androidTarget {
		@OptIn(ExperimentalKotlinGradlePluginApi::class)
		compilerOptions {
			jvmTarget.set(JvmTarget.JVM_21)
		}
	}
	
	jvm("desktop") {
		@OptIn(ExperimentalKotlinGradlePluginApi::class)
		compilerOptions {
			jvmTarget.set(JvmTarget.JVM_21)
		}
	}
	
	listOf(
		iosX64(),
		iosArm64(),
		iosSimulatorArm64()
	).forEach { iosTarget ->
		iosTarget.binaries.framework {
			baseName = "SampleApp"
			isStatic = true
		}
	}
	
	sourceSets {
		val desktopMain by getting
		
		androidMain.dependencies {
			implementation(compose.preview)
			implementation(libs.activity.compose)
		}
		commonMain.dependencies {
			implementation(compose.runtime)
			implementation(compose.foundation)
			implementation(compose.material)
			implementation(compose.ui)
			implementation(compose.components.resources)
			implementation(compose.components.uiToolingPreview)
			implementation(projects.configApi)
		}
		desktopMain.dependencies {
			implementation(compose.desktop.currentOs)
		}
	}
}

val NamedDomainObjectContainer<KotlinSourceSet>.desktopMain: KotlinSourceSet
	get() = this.getByName("desktopMain")

android {
	namespace = "cn.vividcode.multiplatform.config.sample"
	compileSdk = libs.versions.android.compileSdk.get().toInt()
	
	sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
	sourceSets["main"].res.srcDirs("src/androidMain/res")
	sourceSets["main"].resources.srcDirs("src/commonMain/resources")
	
	defaultConfig {
		applicationId = "cn.vividcode.multiplatform.config.sample"
		minSdk = libs.versions.android.minSdk.get().toInt()
		targetSdk = libs.versions.android.targetSdk.get().toInt()
		versionCode = 1
		versionName = configVersion
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_21
		targetCompatibility = JavaVersion.VERSION_21
	}
	buildFeatures {
		compose = true
	}
	dependencies {
		debugImplementation(compose.uiTooling)
	}
}

compose.desktop {
	application {
		mainClass = "cn.vividcode.multiplatform.config.sample.MainKt"
		
		nativeDistributions {
			targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
			packageName = "cn.vividcode.multiplatform.config.sample"
			packageVersion = configVersion
		}
	}
}