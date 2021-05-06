package com.github.ricardopolit.sipmanager.util

import kotlin.random.Random

object RandomGenerator {

    fun getRandomString(length: Int, withNumbers: Boolean = false): String{
        var allowedChars = ('A'..'Z')+('a'..'z')
        if(withNumbers)
            allowedChars += ('0'..'9')

        return (1..length)
                .map{ allowedChars.random() }
                .joinToString("")
    }

    fun getRandomPhrase(length: Int, withNumbers: Boolean = false): String{
        return (1..length).joinToString(" ") {
            getRandomString( Random.nextInt(4,9) ) }
    }

}