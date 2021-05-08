package com.github.ricardopolit.sipmanager

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.ricardopolit.sipmanager.data.SIPManagerDatabase
import com.github.ricardopolit.sipmanager.data.deposit.DepositRepository
import com.github.ricardopolit.sipmanager.data.portfolio.PortfolioRepository
import kotlinx.coroutines.*
import org.junit.*
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
class DepositRepositoryTest {

    private lateinit var depositRepository: DepositRepository
    private lateinit var portfolioRepository: PortfolioRepository

    @Before
    fun setup(){
        SIPManagerDatabase.TEST_MODE = true
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = SIPManagerDatabase.getInstance(context,"test")
        depositRepository = DepositRepository.getInstance(
                db.depositDAO,
                db.portfolioDAO
        )

        portfolioRepository = PortfolioRepository.getInstance(
                db.portfolioDAO
        )

        runBlocking {

            val portfolio = CreatorOfModelsTest.makeOnePortfolio(idPort = 1)

            portfolioRepository.insertPortfolio(portfolio)

            val deposits = CreatorOfModelsTest.makeMultipleDeposit(10,1)

            for(deposit in deposits)
                depositRepository.insertDeposit(deposit)

            val depositsTest = getValue(depositRepository.getAllDeposits())
            printList(depositsTest)
        }

    }

    @After
    fun tearDown(){
        runBlocking {
            portfolioRepository.clearAllPortfolios()
            depositRepository.clearAllDeposits()
        }
    }

    @Test
    fun should_Insert_Deposit_Item(){
        val portfolio = CreatorOfModelsTest.makeOnePortfolio("Portafolio test",idPort = 2)
        runBlocking {
            portfolioRepository.insertPortfolio(portfolio)
            depositRepository.insertDeposit(
                    CreatorOfModelsTest.makeOneDeposit(idPortfolio = 2)
            )
            val depositsTest = getValue(depositRepository.getAllDeposits())
            printList( depositsTest )
            Assert.assertEquals(2, depositsTest.last().idPortfolio)
        }
    }

    @Test
    fun should_update_Deposit_item(){
        runBlocking {
            val deposits = getValue(depositRepository.getAllDeposits())
            val deposit = deposits.get( Random.nextInt(1, 10) )
            val depositToUpdate = depositRepository.getDeposit(deposit.id)
            depositToUpdate?.amount = 999999F
            depositRepository.updateDeposit(depositToUpdate!!)
            val depositTest = depositRepository.getDeposit(deposit.id)
            Assert.assertEquals(999999F, depositTest?.amount)
        }
    }

    @Test
    fun should_clear_deposit_item(){

        runBlocking {
            val deposits = getValue(depositRepository.getAllDeposits())
            val deposit = deposits.get( Random.nextInt(1, 10) )
            depositRepository.clearDeposit(deposit.id)
            val depositTest = depositRepository.getDeposit(deposit.id)
            Assert.assertEquals(null, depositTest)
        }
    }

    @Test
    fun should_delete_deposit_item(){
        runBlocking {
            val deposits = getValue(depositRepository.getAllDeposits())
            val deposit = deposits.get( Random.nextInt(1, 10) )
            depositRepository.deleteDeposit(deposit.id)
            val depositTest = depositRepository.getDeposit(deposit.id)
            Assert.assertEquals(false, depositTest?.active)
        }
    }

    @Test
    fun should_recover_deposit_item(){
        runBlocking {
            val deposits = getValue(depositRepository.getAllDeposits())
            val deposit = deposits.get( Random.nextInt(1, 10) )
            depositRepository.deleteDeposit(deposit.id)
            val depositTest1 = depositRepository.getDeposit(deposit.id)
            Assert.assertEquals(false, depositTest1?.active)
            depositRepository.recoverDeposit(deposit.id)
            val depositTest2 = depositRepository.getDeposit(deposit.id)
            Assert.assertEquals(true, depositTest2?.active)
        }
    }

    @Test
    fun should_clearAll_deposits_from_portfolio(){
        runBlocking {
            portfolioRepository.insertPortfolio(
                    CreatorOfModelsTest.makeOnePortfolio(idPort = 2)
            )
            depositRepository.insertDeposit(
                    CreatorOfModelsTest.makeOneDeposit(idPortfolio = 2)
            )
            depositRepository.clearAllDepositsFromPortfolio(1)
            val depositsTest = getValue(depositRepository.getAllDeposits())
            Assert.assertEquals( 1, depositsTest.size)
        }
    }

    @Test
    fun should_deleteAll_deposits_from_portfolio(){
        runBlocking {
            var aux = 0
            depositRepository.deleteAllDepositsFromPortfolio(1)
            val depositsTest = getValue(depositRepository.getAllDepositsFromPortfolioHistory(1))

            for(depositTest in depositsTest)
                if( !depositTest.active )
                    aux++

            Assert.assertEquals( 10, aux)
        }
    }

    @Test
    fun should_recoverAll_deposits_from_portfolio(){
        runBlocking {
            var aux = 0
            should_deleteAll_deposits_from_portfolio()
            depositRepository.recoverAllDepositsFromPortfolio(1)
            val depositsTest = getValue(depositRepository.getAllDepositsFromPortfolioHistory(1))

            for(depositTest in depositsTest)
                if( depositTest.active )
                    aux++

            Assert.assertEquals( 10, aux)
        }
    }

    @Test
    fun should_getAll_portfolios_with_deposits(){
        runBlocking {

            val depositsTest = getValue(portfolioRepository.getAllPortfoliosWithDeposits())
            Assert.assertEquals(1, depositsTest.size)
            Assert.assertEquals(1L, depositsTest[0].portfolio.id)
            Assert.assertEquals(10, depositsTest[0].deposits.size)
        }
    }

    @Test
    fun should_get_portfolio_with_deposits(){
        runBlocking {

            val depositsTest = portfolioRepository.getPortfolioWithDeposits(1)
            Assert.assertEquals(1L, depositsTest?.portfolio?.id)
            Assert.assertEquals(10, depositsTest?.deposits?.size)
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