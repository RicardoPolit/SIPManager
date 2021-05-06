package com.github.ricardopolit.sipmanager.util

import com.github.ricardopolit.sipmanager.data.portfolio.Portfolio
import kotlin.random.Random

object CreatorOfTestPortfolio {

    var numberOfPortfolios = 1L

    fun makeMultiple(quantity: Int): List<Portfolio>{
        val portfolios = mutableListOf<Portfolio>()

        for(i in 1..quantity)
            portfolios.add( makeOne() )

        return portfolios
    }

    fun makeOne(nameWant: String? = null,idPort: Long = -1L): Portfolio{
        val myId = if(idPort != -1L) idPort else numberOfPortfolios++
        return Portfolio(
//                id = myId,
                name = nameWant ?: RandomGenerator.getRandomPhrase(2),
                goal = RandomGenerator.getRandomPhrase(4),
                dateFinish = Random.nextLong(1000,1000000),
                totalDeposits = Random.nextFloat() + Random.nextInt(10,1000),
                earnings = Random.nextFloat() + Random.nextInt(10,1000),
                color = RandomGenerator.getRandomPhrase(1),
                currency = RandomGenerator.getRandomString(3)
        )

    }

}