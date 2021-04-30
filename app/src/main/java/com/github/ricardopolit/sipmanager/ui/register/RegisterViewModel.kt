package com.github.ricardopolit.sipmanager.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.ricardopolit.sipmanager.data.SIPManagerDatabase
import com.github.ricardopolit.sipmanager.util.HashString
import com.github.ricardopolit.sipmanager.util.Preference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(
    application: Application) : AndroidViewModel(application) {

    var password = ""
    var recoverQuestion = ""
    var recoverAnswer = ""
    var enableBiometric = false

    private var _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin : LiveData<Boolean>
        get() = _navigateToLogin

    private suspend fun setCredentials(){
        withContext(Dispatchers.IO){
            Preference.storePreferenceString(getApplication<Application>().applicationContext,
                    "com.github.ricardopolit.sipmanager.RECOVER_QUESTION", recoverQuestion)

            val encryptedAnswer = HashString.hashValue(recoverAnswer)
            val encryptedPassword = HashString.hashValue(password)

            Preference.storePreferenceString(getApplication<Application>().applicationContext,
                    "com.github.ricardopolit.sipmanager.RECOVER_ANSWER", encryptedAnswer)

            Preference.storePreferenceString(getApplication<Application>().applicationContext,
                    "com.github.ricardopolit.sipmanager.HASHED_PASSWORD", encryptedPassword)

            Preference.storePreferenceFlag(getApplication<Application>().applicationContext,
                    "com.github.ricardopolit.sipmanager.BIOMETRIC_FLAG", enableBiometric)


            SIPManagerDatabase.getInstance(getApplication<Application>().applicationContext,
                    password)


            Preference.storePreferenceFlag(getApplication<Application>().applicationContext,
                    "DB_EXISTS", true)
        }

    }

    fun onSetCredentialsClicked( ){

        viewModelScope.launch {

            setCredentials()

        }.invokeOnCompletion {
            _navigateToLogin.value = true
        }

    }

    fun doneNavigating(){
        _navigateToLogin.value = false
    }

}