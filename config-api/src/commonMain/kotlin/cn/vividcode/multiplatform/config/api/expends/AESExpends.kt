package cn.vividcode.multiplatform.config.api.expends

import cn.vividcode.multiplatform.config.api.Configure
import korlibs.crypto.AES
import korlibs.crypto.CipherPadding
import korlibs.crypto.encoding.base64
import korlibs.crypto.encoding.fromBase64

private val aesKey by lazy { Configure.aesKey.encodeToByteArray() }

private val aesIv by lazy { Configure.aesIv.encodeToByteArray() }

/**
 * encrypt
 */
internal val String.encrypt: String
	get() = AES.encryptAesCbc(
		data = this.encodeToByteArray(),
		key = aesKey,
		iv = aesIv,
		padding = CipherPadding.PKCS7Padding
	).base64

/**
 * decrypt
 */
internal val String.decrypt: String
	get() = AES.decryptAesCbc(
		data = this.fromBase64(),
		key = aesKey,
		iv = aesIv,
		padding = CipherPadding.PKCS7Padding
	).decodeToString()