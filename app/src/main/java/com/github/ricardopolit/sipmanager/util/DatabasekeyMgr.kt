package com.github.ricardopolit.sipmanager.util

import android.content.Context
import android.os.Build
import android.util.Base64
import java.lang.StringBuilder
import java.security.AlgorithmParameters
import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object DatabasekeyMgr {

    private val HEX_CHARS = "0123456789ABCDEF".toCharArray()
    private lateinit var rawByteKey: ByteArray
    private var dbCharKey: CharArray? = null

    private fun generateRandomKey(): ByteArray =
            ByteArray(32).apply {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    SecureRandom.getInstanceStrong().nextBytes(this)
                }else{
                    SecureRandom().nextBytes(this)
                }
            }

    private fun ByteArray.toHex(): CharArray{
        val result = StringBuilder()
        forEach {
            val octet = it.toInt()
            val firstIndex = (octet and 0xF0).ushr(4)
            val secondIndex = octet and 0x0F
            result.append(HEX_CHARS[firstIndex])
            result.append(HEX_CHARS[secondIndex])
        }
        return result.toString().toCharArray()
    }

    private fun createNewKey(){
        rawByteKey = generateRandomKey()
        dbCharKey = rawByteKey.toHex()
    }

    private fun generateSecretKey(password: CharArray, salt: ByteArray): SecretKey{
        val factory: SecretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val spec: KeySpec = PBEKeySpec(password,salt,65536,256)
        val tmp: SecretKey = factory.generateSecret(spec)
        return SecretKeySpec(tmp.encoded,"AES")
    }

    private fun toStorable(rawDbKey: ByteArray, userPassword: CharArray): Storable{
        val salt = ByteArray(8).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                SecureRandom.getInstanceStrong().nextBytes(this)
            } else {
                SecureRandom().nextBytes(this)
            }
        }
        val secret: SecretKey = generateSecretKey(userPassword, salt)

        val cipher: Cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE,secret)
//        val params: AlgorithmParameters = cipher.parameters
//        val iv: ByteArray = params.getParameterSpec(IvParameterSpec::class.java).iv
        val iv: ByteArray = cipher.iv
        val cipherText: ByteArray = cipher.doFinal(rawDbKey)

        return Storable(
                Base64.encodeToString(iv,Base64.DEFAULT),
                Base64.encodeToString(cipherText,Base64.DEFAULT),
                Base64.encodeToString(salt,Base64.DEFAULT)
        )
    }

    private fun persistRawKey(userPassword: CharArray, context: Context){
        val storable = toStorable(rawByteKey,userPassword)
        Preference.storeEncryptedKeyDb(
                context,
                "com.github.ricardopolit.sipmanager.DB_KEY",
                storable)
    }

    private fun getRawByteKey(password: CharArray, storable: Storable ): ByteArray{
        val aesWrappedKey = Base64.decode(storable.key, Base64.DEFAULT)
        val iv = Base64.decode(storable.iv, Base64.DEFAULT)
        val salt = Base64.decode(storable.salt, Base64.DEFAULT)
        val secret: SecretKey = generateSecretKey(password,salt)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, secret, IvParameterSpec(iv))
        return cipher.doFinal(aesWrappedKey)
    }

    fun getCharKey(password: String, context: Context): CharArray{
        if(dbCharKey == null){
            initKey(password.toCharArray(), context)
        }
        return dbCharKey ?: error("Failed to decrypt database key")
    }

    private fun initKey(password: CharArray, context: Context){
        val storable = Preference.getEncryptedKeyDb(context, "com.github.ricardopolit.sipmanager.DB_KEY")
        if( storable == null ){
            createNewKey()
            persistRawKey(password,context)
        }else{
            rawByteKey = getRawByteKey(password,storable)
            dbCharKey = rawByteKey.toHex()
        }
    }

}