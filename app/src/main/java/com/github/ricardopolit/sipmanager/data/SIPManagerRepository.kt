package com.github.ricardopolit.sipmanager.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SIPManagerRepository private constructor(
        private val portfolioDAO: PortfolioDAO,
        private val depositDAO: DepositDAO,
        private val appDAO: AppDAO){

    //----------------------------------------------------------------------Deposit methods
    suspend fun insertDeposit(deposit: Deposit){
        depositDAO.insert(deposit)
    }

    suspend fun updateDeposit(deposit: Deposit){
        depositDAO.update(deposit)
    }

    suspend fun clearAllDeposits(){
        depositDAO.clearAllDeposits()
    }

    suspend fun clearDeposit(id: Long){
        depositDAO.clearDeposit(id)
    }

    suspend fun deleteDeposit(id: Long){
        depositDAO.deleteDeposit(id)
    }

    suspend fun deleteAllDeposits(){
        depositDAO.deleteAllDeposits()
    }

    suspend fun getAllDepositsHistory(): LiveData<List<Deposit>>{
        val allDeposits = MutableLiveData<List<Deposit>>()
        allDeposits.value = depositDAO.getAllHistoryDeposits()
        return allDeposits
    }

    suspend fun getAllDeposits(): LiveData<List<Deposit>>{
        val allDeposits = MutableLiveData<List<Deposit>>()
        allDeposits.value = depositDAO.getAllDeposits()
        return allDeposits
    }

    suspend fun getDeposit(id: Long): LiveData<Deposit>{
        val deposit = MutableLiveData<Deposit>()
        deposit.value = depositDAO.getDeposit(id)
        return deposit
    }

    suspend fun getAllDepositsFromPortfolio(idPortfolio: Long): LiveData<List<Deposit>>{
        val allDepositsFromPortfolio = MutableLiveData<List<Deposit>>()
        allDepositsFromPortfolio.value = depositDAO.getAllDepositsFromPortfolio(idPortfolio)
        return allDepositsFromPortfolio
    }

    suspend fun getAllDepositsFromPortfolioHistory(idPortfolio: Long): LiveData<List<Deposit>>{
        val allDepositsFromPortfolioHistory = MutableLiveData<List<Deposit>>()
        allDepositsFromPortfolioHistory.value =
                depositDAO.getAllDepositsFromPortfolioHistory(idPortfolio)
        return allDepositsFromPortfolioHistory
    }

    //----------------------------------------------------------------------App methods
    suspend fun insertApp(app: App){
        appDAO.insert(app)
    }

    suspend fun updateApp(app: App){
        appDAO.update(app)
    }

    suspend fun clearAllApps(){
        appDAO.clearAllApps()
    }

    suspend fun clearApp(id: Long){
        appDAO.clearApp(id)
    }

    suspend fun deleteApp(id: Long){
        appDAO.deleteApp(id)
    }

    suspend fun deleteAllApps(){
        appDAO.deleteAllApps()
    }

    suspend fun getAllAppsHistory(): LiveData<List<App>>{
        val allApps = MutableLiveData<List<App>>()
        allApps.value = appDAO.getAllHistoryApps()
        return allApps
    }

    suspend fun getAllApps(): LiveData<List<App>>{
        val allApps = MutableLiveData<List<App>>()
        allApps.value = appDAO.getAllApps()
        return allApps
    }

    suspend fun getApp(id: Long): LiveData<App>{
        val app = MutableLiveData<App>()
        app.value = appDAO.getApp(id)
        return app
    }

    //------------------------------------------------------------------Portfolio methods
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

    suspend fun getAllPortfoliosHistory(): LiveData<List<Portfolio>>{
        val allPortfolios = MutableLiveData<List<Portfolio>>()
        allPortfolios.value = portfolioDAO.getAllHistoryPortfolios()
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

    suspend fun getAllPortfoliosWithDeposits(): LiveData<List<PortfolioWithDeposits>>{
        val allPortfoliosWithDeposits = MutableLiveData<List<PortfolioWithDeposits>>()
        allPortfoliosWithDeposits.value = portfolioDAO.getPortfoliosWithDeposits()
        return allPortfoliosWithDeposits
    }

    suspend fun getPortfolioWithDeposits(id: Long): LiveData<PortfolioWithDeposits>{
        val portfolioWithDeposits = MutableLiveData<PortfolioWithDeposits>()
        portfolioWithDeposits.value = portfolioDAO.getPortfolioWithDeposits(id)
        return portfolioWithDeposits
    }

    companion object{
        @Volatile private var instance: SIPManagerRepository? = null

        fun getInstance(
                portfolioDAO: PortfolioDAO,
                depositDAO: DepositDAO,
                appDAO: AppDAO) =
                instance ?: synchronized(this){
                    instance ?: SIPManagerRepository(
                            portfolioDAO,
                            depositDAO,
                            appDAO).also { instance = it }
                }
    }

}