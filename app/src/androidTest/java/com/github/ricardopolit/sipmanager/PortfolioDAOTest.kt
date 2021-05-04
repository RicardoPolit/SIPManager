package com.github.ricardopolit.sipmanager

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.ricardopolit.sipmanager.data.SIPManagerDatabase
import com.github.ricardopolit.sipmanager.data.portfolio.Portfolio
import com.github.ricardopolit.sipmanager.data.portfolio.PortfolioDAO
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PortfolioDAOTest {

    private lateinit var portfolioDAO: PortfolioDAO

    @Before
    fun setup(){
        SIPManagerDatabase.TEST_MODE = true
        val context = ApplicationProvider.getApplicationContext<Context>()
        portfolioDAO = SIPManagerDatabase.getInstance(context,"test").portfolioDAO
    }

    @After
    fun tearDown(){

    }

    @Test
    fun should_Insert_Portfolio_Item(){
        val portfolio = Portfolio(
            id = 1,
            name = "Portafolio prueba",
            goal = "Este es el objetivo",
            dateFinish = System.currentTimeMillis()+1000L,
            totalDeposits = 10000F,
            earnings = 0.10F,
            color = R.color.black.toString(),
            currency = "MXN"
        )

        runBlocking {
            portfolioDAO.insert(portfolio)
            val portfolioTest = portfolioDAO.getPortfolio(portfolio.id)
            println("should_insert_portfolio_item values: \n" +
                    " portfolio.name = ${portfolio.name} \n" +
                    " portfolioTest.name = ${portfolioTest?.name}")
            Assert.assertEquals(portfolio.name,portfolioTest?.name)
        }

    }

    @Test
    fun should_delete_all_portfolios(){
        runBlocking {
            portfolioDAO.clearAllPortfolios()
            val allPortfoliosTest = portfolioDAO.getAllPortfolios()
            Assert.assertEquals( allPortfoliosTest.value,null )
        }
    }

}