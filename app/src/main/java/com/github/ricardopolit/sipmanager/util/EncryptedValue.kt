package com.github.ricardopolit.sipmanager.util

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EncryptedValue(
        val cipherText: ByteArray,
        val initializationVector: ByteArray
) : Parcelable
