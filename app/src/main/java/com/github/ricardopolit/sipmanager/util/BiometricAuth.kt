package com.github.ricardopolit.sipmanager.util

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat

object BiometricAuth {

    fun isBiometricReady(context: Context) =
        BiometricManager.from(context).canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS

    fun isBiometricCapable(context: Context) =
        BiometricManager.from(context).canAuthenticate() == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED

    fun setBiometricPromptInfo(
        title: String,
        subtitle: String,
        allowDeviceCredential: Boolean
    ): BiometricPrompt.PromptInfo{
        val builder = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(subtitle)

        builder.apply {
            if(allowDeviceCredential) setDeviceCredentialAllowed(true)
            else setNegativeButtonText("Cancel")
        }

        return builder.build()
    }

    fun initBiometricPrompt(
        activity: AppCompatActivity,
        listener: BiometricAuthListener
    ): BiometricPrompt {

        val executor = ContextCompat.getMainExecutor(activity)

        val callback = object : BiometricPrompt.AuthenticationCallback(){

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                listener.onBiometricAuthenticationError(errorCode,errString.toString())
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Log.w(this.javaClass.simpleName,"Authentication failed for an unknown reason")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                listener.onBiometricAuthenticationSuccess(result)
            }
        }

        return BiometricPrompt(activity,executor,callback)
    }

    fun showBiometricPrompt(
        title: String = "Biometric Authentication",
        subtitle: String = "Enter biometric credentials to proceed",
        activity: AppCompatActivity,
        listener: BiometricAuthListener,
        cryptoObject: BiometricPrompt.CryptoObject? = null,
        allowDeviceCredential: Boolean = false
    ){

        val promptInfo = setBiometricPromptInfo(
            title,
            subtitle,
            allowDeviceCredential
        )

        val biometricPrompt = initBiometricPrompt(activity,listener)

        biometricPrompt.apply {
            if(cryptoObject == null) authenticate(promptInfo)
            else authenticate(promptInfo, cryptoObject)
        }

    }

}