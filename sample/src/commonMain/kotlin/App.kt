import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.vividcode.multiplatform.config.api.Config
import cn.vividcode.multiplatform.config.api.config
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
	LaunchedEffect(Unit) {
		Config.appName = "ConfigSample"
	}
	MaterialTheme {
		val coroutineScope = rememberCoroutineScope()
		val scaffoldState = rememberScaffoldState()
		Scaffold(
			modifier = Modifier
				.fillMaxSize(),
			scaffoldState = scaffoldState
		) {
			Column(
				modifier = Modifier
					.fillMaxSize(),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
			) {
				var value by remember { mutableStateOf("") }
				var type by remember { mutableStateOf("String") }
				Text("支持的数据类型\nString, Short, Int, Long, Float, Double, Boolean")
				Spacer(modifier = Modifier.height(16.dp))
				Row(
					modifier = Modifier
						.width(400.dp)
				) {
					OutlinedTextField(
						value = value,
						onValueChange = { value = it },
						modifier = Modifier
							.weight(1F),
						label = {
							Text("${type}类型测试")
						},
						singleLine = true
					)
					Spacer(modifier = Modifier.width(16.dp))
					OutlinedTextField(
						value = type,
						onValueChange = { type = it },
						modifier = Modifier
							.width(100.dp),
						label = {
							Text("数据类型")
						},
						singleLine = true
					)
				}
				Spacer(modifier = Modifier.height(16.dp))
				Row(
					modifier = Modifier
						.width(400.dp)
				) {
					Button(
						onClick = {
							try {
								when (type) {
									"String" -> SampleConfig.testString = value
									"Short" -> SampleConfig.testShort = value.toShort()
									"Int" -> SampleConfig.testInt = value.toInt()
									"Long" -> SampleConfig.testLong = value.toLong()
									"Float" -> SampleConfig.testFloat = value.toFloat()
									"Double" -> SampleConfig.testDouble = value.toDouble()
									"Boolean" -> SampleConfig.testBoolean = value.toBooleanStrict()
									else -> throw Exception()
								}
								coroutineScope.launch {
									scaffoldState.snackbarHostState.showSnackbar("已保存")
								}
							} catch (e: Exception) {
								println(e.message)
								coroutineScope.launch {
									scaffoldState.snackbarHostState.showSnackbar("数据类型错误")
								}
							}
						},
						modifier = Modifier
							.weight(1F)
					) {
						Text(text = "保存")
					}
					Spacer(modifier = Modifier.width(16.dp))
					Button(
						onClick = {
							value = when (type) {
								"String" -> SampleConfig.testString
								"Short" -> SampleConfig.testShort
								"Int" -> SampleConfig.testInt
								"Long" -> SampleConfig.testLong
								"Float" -> SampleConfig.testFloat
								"Double" -> SampleConfig.testDouble
								"Boolean" -> SampleConfig.testBoolean
								else -> {
									coroutineScope.launch {
										scaffoldState.snackbarHostState.showSnackbar("数据类型错误")
									}
									""
								}
							}.toString()
						},
						modifier = Modifier
							.weight(1F)
					) {
						Text(text = "获取")
					}
				}
			}
		}
	}
}

object SampleConfig {
	
	var testString: String by config()
	
	var testShort: Short by config()
	
	var testInt: Int by config()
	
	var testLong: Long by config()
	
	var testFloat: Float by config()
	
	var testDouble: Double by config()
	
	var testBoolean: Boolean by config()
}