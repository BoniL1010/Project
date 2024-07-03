package com.example.myapplication.util

import android.util.Base64
import android.util.Log
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object DataCoder {
    private const val TAG = "ActiveTask"
    private const val CODER_ALGORITHM = "AES"
    private const val CODER_TRANSFORMATION = "AES/CBC/PKCS5PADDING"
    private const val KEY = "ActiveFenceSecre"
    private const val IV = "qwertyZXCVBN1234"


    fun encryptData(data: String): String {
        try {
            val keySpec = SecretKeySpec(KEY.toByteArray(), CODER_ALGORITHM)
            val ivSpec = IvParameterSpec(IV.toByteArray())

            val cipher = Cipher.getInstance(CODER_TRANSFORMATION)
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)

            val encryptedBytes = cipher.doFinal(data.toByteArray())
            val encryptedBase64 = Base64.encodeToString(encryptedBytes, Base64.DEFAULT)

            Log.d(TAG, encryptedBase64)

            return encryptedBase64

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    fun decryptData(encryptedData: String): String {
        try {
            val encryptedBytes = Base64.decode(encryptedData, Base64.DEFAULT)

            val cipher = Cipher.getInstance(CODER_TRANSFORMATION)

            val secretKeySpec = SecretKeySpec(KEY.toByteArray(), CODER_ALGORITHM)
            val ivParameterSpec = IvParameterSpec(IV.toByteArray())

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)

            val decryptedBytes = cipher.doFinal(encryptedBytes)

            return String(decryptedBytes, Charsets.UTF_8)


        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }
}