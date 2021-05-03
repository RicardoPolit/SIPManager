package com.github.ricardopolit.sipmanager.data.portfolio

import androidx.lifecycle.LiveData

class PortfolioRepository private constructor(
        private val portfolioDAO: PortfolioDAO){

    companion object{
        @Volatile private var instance: PortfolioRepository? = null

        fun getInstance(portfolioDAO: PortfolioDAO) =
                instance ?: synchronized(this){
                    instance ?: PortfolioRepository(portfolioDAO).also { instance = it }
                }
    }

    suspend fun insertPortfolio(portfolio: Portfolio){
        portfolioDAO.insert(portfolio)
    }

    suspend fun updatePortfolio(portfolio: Portfolio){
        portfolioDAO.update(portfolio)
    }

    suspend fun clearAllPortfolios(){
        portfolioDAO.clearAllPortfolios()
    }

    suspend fun clearPortfolio(id: Long){
        portfolioDAO.clearPortfolio(id)
    }

    suspend fun deletePortfolio(id: Long){
        portfolioDAO.deletePortfolio(id)
    }

    suspend fun recoverPortfolio(id: Long){
        portfolioDAO.recoverPortfolio(id)
    }

    fun getAllPortfoliosHistory(): LiveData<List<Portfolio>> {
        return portfolioDAO.getAllHistoryPortfolios()
    }

    fun getAllPortfolios(): LiveData<List<Portfolio>> {
        return portfolioDAO.getAllPortfolios()
    }

    suspend fun getPortfolio(id: Long): Portfolio? {
        return portfolioDAO.getPortfolio(id)
    }

    fun getAllPortfoliosWithDeposits(): LiveData<List<PortfolioWithDeposits>> {
        return portfolioDAO.getPortfoliosWithDeposits()
    }

    fun getAllPortfoliosHistoryWithDeposits(): LiveData<List<PortfolioWithDeposits>> {
        return portfolioDAO.getPortfoliosHistoryWithDeposits()
    }

    suspend fun getPortfolioWithDeposits(id: Long): PortfolioWithDeposits? {
        return portfolioDAO.getPortfolioWithDeposits(id)
    }

    fun getAllPortfoliosWithSections(): LiveData<List<PortfolioWithSections>> {
        return portfolioDAO.getPortfoliosWithSections()
    }

    fun getAllPortfoliosHistoryWithSections(): LiveData<List<PortfolioWithSections>> {
        return portfolioDAO.getPortfoliosHistoryWithSections()
    }

    suspend fun getPortfolioWithSections(id: Long): PortfolioWithSections? {
        return portfolioDAO.getPortfolioWithSections(id)
    }

}