package com.github.ricardopolit.sipmanager

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.ricardopolit.sipmanager.data.SIPManagerDatabase
import com.github.ricardopolit.sipmanager.data.portfolio.Portfolio
import com.github.ricardopolit.sipmanager.data.portfolio.PortfolioDAO
import com.github.ricardopolit.sipmanager.data.portfolio.PortfolioRepository
import com.github.ricardopolit.sipmanager.util.CreatorOfTestPortfolio
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

        val portfolios = CreatorOfTestPortfolio.makeMultiple(10)

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
        val portfolio = CreatorOfTestPortfolio.makeOne("Portafolio test")
        runBlocking {
            portfolioRepository.insertPortfolio(portfolio)
            val portfoliosTest = getValue(portfolioRepository.getAllPortfoliosHistory())
            printList( portfoliosTest )
            Assert.assertEquals(portfoliosTest.last().name,"Portafolio test")
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
            Assert.assertEquals(portfolioTest?.name,"Portfolio prueba actualizado")
        }
    }

    @Test
    fun should_clear_portfolio_item(){

        runBlocking {
            val portfolios = getValue(portfolioRepository.getAllPortfolios())
            val portfolio = portfolios.get( Random.nextInt(1, 10) )
            portfolioRepository.clearPortfolio(portfolio.id)
            val portfolioTest = portfolioRepository.getPortfolio(portfolio.id)
            Assert.assertEquals(portfolioTest,null)
        }
    }

    @Test
    fun should_delete_portfolio_item(){
        runBlocking {
            val portfolios = getValue(portfolioRepository.getAllPortfolios())
            val portfolio = portfolios.get( Random.nextInt(1, 10) )
            portfolioRepository.deletePortfolio(portfolio.id)
            val portfolioTest = portfolioRepository.getPortfolio(portfolio.id)
            Assert.assertEquals(portfolioTest?.active,false)
        }
    }

    @Test
    fun should_recover_portfolio_item(){
        runBlocking {
            val portfolios = getValue(portfolioRepository.getAllPortfolios())
            val portfolio = portfolios.get( Random.nextInt(1, 10) )
            portfolioRepository.deletePortfolio(portfolio.id)
            val portfolioTest1 = portfolioRepository.getPortfolio(portfolio.id)
            Assert.assertEquals(portfolioTest1?.active,false)
            portfolioRepository.recoverPortfolio(portfolio.id)
            val portfolioTest2 = portfolioRepository.getPortfolio(portfolio.id)
            Assert.assertEquals(portfolioTest2?.active,true)
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

    fun <T> printList( list: List<T>){
        for(item in list)
            println( item.toString() )
    }

}