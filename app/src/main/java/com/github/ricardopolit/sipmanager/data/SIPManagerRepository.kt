package com.github.ricardopolit.sipmanager.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SIPManagerRepository private constructor(
        private val portfolioDAO: PortfolioDAO){


    suspend fun insertPortfolio(portfolio: Portfolio){
        portfolioDAO.insert(portfolio)
    }


    suspend fun updatePortfolio(portfolio: Portfolio){
        portfolioDAO.update(portfolio)
    }

    suspend fun clearAllPortfolios(){
        portfolioDAO.clearAll()
    }

    suspend fun clearPortfolio(id: Long){
        portfolioDAO.clearPortfolio(id)
    }

    suspend fun deletePortfolio(id: Long){
        portfolioDAO.deletePortfolio(id)
    }

    suspend fun getAllPortfoliosHistory(): LiveData<List<Portfolio>>{
        val allPortfolios = MutableLiveData<List<Portfolio>>()
        allPortfolios.value = portfolioDAO.getAllPortfoliosHistory()
        return allPortfolios
    }

    suspend fun getAllPortfolios(): LiveData<List<Portfolio>>{
        val allPortfolios = MutableLiveData<List<Portfolio>>()
        allPortfolios.value = portfolioDAO.getAllPortfolios()
        return allPortfolios
    }

    suspend fun getPortfolio(id: Long): LiveData<Portfolio>{
        val portfolio = MutableLiveData<Portfolio>()
        portfolio.value = portfolioDAO.getPortfolio(id)
        return portfolio
    }

    companion object{
        @Volatile private var instance: SIPManagerRepository? = null

        fun getInstance(portfolioDAO: PortfolioDAO) =
                instance ?: synchronized(this){
                    instance ?: SIPManagerRepository(portfolioDAO).also { instance = it }
                }
    }

}