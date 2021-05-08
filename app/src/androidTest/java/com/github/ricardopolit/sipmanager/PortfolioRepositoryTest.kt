package com.github.ricardopolit.sipmanager

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.ricardopolit.sipmanager.data.SIPManagerDatabase
import com.github.ricardopolit.sipmanager.data.portfolio.PortfolioRepository
import kotlinx.coroutines.*
import org.junit.*
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.random.Random


@RunWith(AndroidJUnit4::class)
class PortfolioRepositoryTest {

    private lateinit var portfolioRepository: PortfolioRepository

    @Before
    fun setup(){
        SIPManagerDatabase.TEST_MODE = true
        val context = ApplicationProvider.getApplicationContext<Context>()
        portfolioRepository = PortfolioRepository.
            getInstance(SIPManagerDatabase.getInstance(context,"test").portfolioDAO)

        val portfolios = CreatorOfModelsTest.makeMultiplePortfolio(10)

        runBlocking {
            portfolioRepository.insertPortfolios(portfolios)
            val portfoliosTest = getValue(portfolioRepository.getAllPortfolios())
            printList(portfoliosTest)
        }


    }

    @After
    fun tearDown(){
        runBlocking {portfolioRepository.clearAllPortfolios()}
    }

    @Test
    fun should_Insert_Portfolio_Item(){
        val portfolio = CreatorOfModelsTest.makeOnePortfolio("Portafolio test",idPort = 200)
        runBlocking {
            portfolioRepository.insertPortfolio(portfolio)
            val portfoliosTest = getValue(portfolioRepository.getAllPortfoliosHistory())
            printList( portfoliosTest )
            Assert.assertEquals("Portafolio test", portfoliosTest.last().name)
        }
    }

    @Test
    fun should_update_portfolio_item(){
        runBlocking {
            val portfolios = getValue(portfolioRepository.getAllPortfolios())
            val portfolio = portfolios.get( Random.nextInt(1, 10) )
            val portfolioToUpdate = portfolioRepository.getPortfolio(portfolio.id)
            portfolioToUpdate?.name = "Portfolio prueba actualizado"
            portfolioRepository.updatePortfolio(portfolioToUpdate!!)
            val portfolioTest = portfolioRepository.getPortfolio(portfolio.id)
            Assert.assertEquals("Portfolio prueba actualizado", portfolioTest?.name)
        }
    }

    @Test
    fun should_clear_portfolio_item(){

        runBlocking {
            val portfolios = getValue(portfolioRepository.getAllPortfolios())
            val portfolio = portfolios.get( Random.nextInt(1, 10) )
            portfolioRepository.clearPortfolio(portfolio.id)
            val portfolioTest = portfolioRepository.getPortfolio(portfolio.id)
            Assert.assertEquals(null, portfolioTest)
        }
    }

    @Test
    fun should_delete_portfolio_item(){
        runBlocking {
            val portfolios = getValue(portfolioRepository.getAllPortfolios())
            val portfolio = portfolios.get( Random.nextInt(1, 10) )
            portfolioRepository.deletePortfolio(portfolio.id)
            val portfolioTest = portfolioRepository.getPortfolio(portfolio.id)
            Assert.assertEquals(false, portfolioTest?.active)
        }
    }

    @Test
    fun should_recover_portfolio_item(){
        runBlocking {
            val portfolios = getValue(portfolioRepository.getAllPortfolios())
            val portfolio = portfolios.get( Random.nextInt(1, 10) )
            portfolioRepository.deletePortfolio(portfolio.id)
            val portfolioTest1 = portfolioRepository.getPortfolio(portfolio.id)
            Assert.assertEquals(false, portfolioTest1?.active)
            portfolioRepository.recoverPortfolio(portfolio.id)
            val portfolioTest2 = portfolioRepository.getPortfolio(portfolio.id)
            Assert.assertEquals(true, portfolioTest2?.active)
        }
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)//To change body of created functions use File | Settings | File Templates.
            }

        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }

    private fun <T> printList( list: List<T>){
        for(item in list)
            println( item.toString() )
    }

}