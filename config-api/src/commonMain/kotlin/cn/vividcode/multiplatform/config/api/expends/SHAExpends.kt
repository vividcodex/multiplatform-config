package cn.vividcode.multiplatform.config.api.expends

import korlibs.crypto.SHA256


/**
 * sha256
 */
internal val String.sha256: String
	get() = SHA256.digest(this.encodeToByteArray()).hexLower