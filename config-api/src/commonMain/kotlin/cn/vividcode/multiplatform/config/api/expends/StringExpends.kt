package cn.vividcode.multiplatform.config.api.expends


internal val UppercaseRegex = Regex("[A-Z]")

/**
 * lowercase With Underline
 */
internal fun String.lowercaseWithUnderline(): String {
	return this.replaceFirstChar { it.lowercase() }
		.replace(UppercaseRegex) { "_${it.value.lowercase()}" }
}