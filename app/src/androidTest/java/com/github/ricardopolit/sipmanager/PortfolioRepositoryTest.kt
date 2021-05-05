package com.github.ricardopolit.sipmanager

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.ricardopolit.sipmanager.data.SIPManagerDatabase
import com.github.ricardopolit.sipmanager.data.portfolio.Portfolio
import com.github.ricardopolit.sipmanager.data.portfolio.PortfolioDAO
import com.github.ricardopolit.sipmanager.data.portfolio.PortfolioRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PortfolioRepositoryTest {

    private lateinit var portfolioRepository: PortfolioRepository

    @Before
    fun setup(){
        SIPManagerDatabase.TEST_MODE = true
        val context = ApplicationProvider.getApplicationContext<Context>()
        portfolioRepository = PortfolioRepository.
            getInstance(SIPManagerDatabase.getInstance(context,"test").portfolioDAO)
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
            portfolioRepository.insertPortfolio(portfolio)
            val portfolioTest = portfolioRepository.getPortfolio(portfolio.id)
            println("should_insert_portfolio_item values: \n" +
                    " portfolio.name = ${portfolio.name} \n" +
                    " portfolioTest.name = ${portfolioTest?.name}")
            Assert.assertEquals(portfolio.name,portfolioTest?.name)
        }

    }

    @Test
    fun should_update_portfolio_item(){
        val portfolio = Portfolio(
                id = 2,
                name = "Portafolio prueba",
                goal = "Este es el objetivo",
                dateFinish = System.currentTimeMillis()+1000L,
                totalDeposits = 10000F,
                earnings = 0.10F,
                color = R.color.black.toString(),
                currency = "MXN"
        )
        runBlocking {
            portfolioRepository.insertPortfolio(portfolio)
            val portfolioToUpdate = portfolioRepository.getPortfolio(portfolio.id)
            portfolioToUpdate?.name = "Portfolio prueba actualizado"
            portfolioRepository.updatePortfolio(portfolioToUpdate!!)
            val portfolioTest = portfolioRepository.getPortfolio(portfolio.id)
            Assert.assertEquals(portfolioTest?.name,"Portfolio prueba actualizado")
        }
    }

    @Test
    fun should_clear_all_portfolios(){
        runBlocking {
            portfolioRepository.clearAllPortfolios()
            val allPortfoliosTest = portfolioRepository.getAllPortfolios()
            Assert.assertEquals( allPortfoliosTest.value,null )
        }
    }

    @Test
    fun should_clear_portfolio_item(){
        val portfolio = Portfolio(
                id = 3,
                name = "Portafolio prueba eliminar",
                goal = "Este es el objetivo eliminar",
                dateFinish = System.currentTimeMillis()+1000L,
                totalDeposits = 1303450F,
                earnings = 0.50F,
                color = R.color.white.toString(),
                currency = "USD"
        )
        runBlocking {
            portfolioRepository.insertPortfolio(portfolio)
            portfolioRepository.clearPortfolio(portfolio.id)
            val portfolioTest = portfolioRepository.getPortfolio(portfolio.id)
            Assert.assertEquals(portfolioTest,null)
        }
    }

    @Test
    fun should_delete_portfolio_item(){
        val portfolio = Portfolio(
                id = 4,
                name = "Portafolio prueba desactivar",
                goal = "Este es el objetivo desactivar",
                dateFinish = System.currentTimeMillis()+1000L,
                totalDeposits = 1303450F,
                earnings = 0.50F,
                color = R.color.white.toString(),
                currency = "RUP"
        )
        runBlocking {
            portfolioRepository.insertPortfolio(portfolio)
            portfolioRepository.deletePortfolio(portfolio.id)
            val portfolioTest = portfolioRepository.getPortfolio(portfolio.id)
            Assert.assertEquals(portfolioTest?.active,false)
            Assert.assertEquals(portfolioTest?.name,"Portafolio prueba desactivar")
        }
    }

    @Test
    fun should_recover_portfolio_item(){
        val portfolio = Portfolio(
                id = 5,
                name = "Portafolio prueba activar",
                goal = "Este es el objetivo activar",
                dateFinish = System.currentTimeMillis()+1000L,
                totalDeposits = 1303450F,
                earnings = 0.50F,
                color = R.color.white.toString(),
                currency = "RUP"
        )
        runBlocking {
            portfolioRepository.insertPortfolio(portfolio)
            portfolioRepository.deletePortfolio(portfolio.id)
            val portfolioTest1 = portfolioRepository.getPortfolio(portfolio.id)
            Assert.assertEquals(portfolioTest1?.active,false)
            Assert.assertEquals(portfolioTest1?.name,"Portafolio prueba activar")
            portfolioRepository.recoverPortfolio(portfolio.id)
            val portfolioTest2 = portfolioRepository.getPortfolio(portfolio.id)
            Assert.assertEquals(portfolioTest2?.active,true)
        }
    }

}