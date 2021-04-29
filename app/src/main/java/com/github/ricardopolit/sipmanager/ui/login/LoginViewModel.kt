package com.github.ricardopolit.sipmanager.ui.login

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.ricardopolit.sipmanager.util.BiometricAuth
import com.github.ricardopolit.sipmanager.util.HashString
import com.github.ricardopolit.sipmanager.util.Preference


class LoginViewModel(
    application: Application) : AndroidViewModel(application) {

    var password = ""
    var biometricEnable = Preference.getPreferenceFlag(application.applicationContext,
        "com.github.ricardopolit.sipmanager.BIOMETRIC_FLAG")

    var buttonLoginBiometricVisibility =
            biometricEnable && BiometricAuth.isBiometricReady( application.applicationContext )

    var buttonRegisterBiometricVisibility =
            biometricEnable && BiometricAuth.isBiometricCapable( application.applicationContext )

    private var _navigateToInvestment = MutableLiveData<Boolean>()
    val navigateToInvestment : LiveData<Boolean>
        get() = _navigateToInvestment

    private var _showSnackBarEventPasswordIncorrect = MutableLiveData<Boolean>()
    val showSnackBarEventPasswordIncorrect : LiveData<Boolean>
        get() = _showSnackBarEventPasswordIncorrect

    fun onLoginClicked(){

        val hashedPassword = Preference.getPreferenceString(
            getApplication<Application>().applicationContext,
            "com.github.ricardopolit.sipmanager.HASHED_PASSWORD" )

        if( HashString.checkValue( password, hashedPassword ) )
            _navigateToInvestment.value = true
        else
            _showSnackBarEventPasswordIncorrect.value = true

    }

    fun doneNavigating(){
        _navigateToInvestment.value = false
    }

    fun doneShowingSnackBarEventPasswordIncorrect(){
        _showSnackBarEventPasswordIncorrect.value = false
    }

}