package com.github.ricardopolit.sipmanager.data.deposit

import androidx.lifecycle.LiveData
import com.github.ricardopolit.sipmanager.data.portfolio.PortfolioDAO

class DepositRepository private constructor(
        private val depositDAO: DepositDAO,
        private val portfolioDAO: PortfolioDAO){
    companion object{
        @Volatile private var instance: DepositRepository? = null

        fun getInstance(depositDAO: DepositDAO,
                        portfolioDAO: PortfolioDAO) =
                instance ?: synchronized(this){
                    instance ?: DepositRepository(depositDAO,
                                                portfolioDAO).also { instance = it }
                }
    }

    suspend fun insertDeposit(deposit: Deposit){

        if(portfolioDAO.getPortfolio(deposit.idPortfolio)!=null)
            depositDAO.insert(deposit)
        else
            error("Deposit insertion error, Portfolio not found")
    }

    suspend fun updateDeposit(deposit: Deposit){
        depositDAO.update(deposit)
    }

    suspend fun clearAllDeposits(){
        depositDAO.clearAllDeposits()
    }

    suspend fun clearAllDepositsFromPortfolio(idPortfolio: Long){
        depositDAO.clearAllDepositsFromPortfolio(idPortfolio)
    }

    suspend fun clearDeposit(id: Long){
        depositDAO.clearDeposit(id)
    }

    suspend fun deleteDeposit(id: Long){
        depositDAO.deleteDeposit(id)
    }

    suspend fun recoverDeposit(id: Long){
        depositDAO.recoverDeposit(id)
    }

//    suspend fun deleteAllDeposits(){
//        depositDAO.deleteAllDeposits()
//    }

    suspend fun deleteAllDepositsFromPortfolio(idPortfolio: Long){
        depositDAO.deleteAllDepositsFromPortfolio(idPortfolio)
    }

    suspend fun recoverAllDepositsFromPortfolio(idPortfolio: Long){
        depositDAO.recoverAllDepositsFromPortfolio(idPortfolio)
    }

    fun getAllDepositsHistory(): LiveData<List<Deposit>> {
        return depositDAO.getAllHistoryDeposits()
    }

    fun getAllDeposits(): LiveData<List<Deposit>> {
        return depositDAO.getAllDeposits()
    }

    suspend fun getDeposit(id: Long): Deposit? {
        return depositDAO.getDeposit(id)
    }

    fun getAllDepositsFromPortfolio(idPortfolio: Long): LiveData<List<Deposit>> {
        return depositDAO.getAllDepositsFromPortfolio(idPortfolio)
    }

    fun getAllDepositsFromPortfolioHistory(idPortfolio: Long): LiveData<List<Deposit>> {
        return depositDAO.getAllDepositsFromPortfolioHistory(idPortfolio)
    }

}