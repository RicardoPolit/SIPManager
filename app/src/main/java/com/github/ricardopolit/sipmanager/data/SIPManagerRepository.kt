package com.github.ricardopolit.sipmanager.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SIPManagerRepository private constructor(
        private val portfolioDAO: PortfolioDAO,
        private val depositDAO: DepositDAO,
        private val sectionDAO: SectionDAO,
        private val assetDAO: AssetDAO,
        private val appDAO: AppDAO){

    //----------------------------------------------------------------------Asset methods
    //TODO finish implementing the assets methods
    suspend fun insertAsset(asset: Asset){

        //if(portfolioDAO.getPortfolio(asset.idPortfolio)!=null)
            assetDAO.insert(asset)
//        else
//            error("Asset insertion error, Portfolio not found")
    }

    suspend fun updateAsset(asset: Asset){
        assetDAO.update(asset)
    }

    //----------------------------------------------------------------------Section methods
    suspend fun insertSection(section: Section){

        if(portfolioDAO.getPortfolio(section.idPortfolio)!=null)
            sectionDAO.insert(section)
        else
            error("Section insertion error, Portfolio not found")
    }

    suspend fun updateSection(section: Section){
        sectionDAO.update(section)
    }

    suspend fun clearAllSections(){
        sectionDAO.clearAllSections()
    }

    suspend fun clearAllSectionsFromPortfolio(idPortfolio: Long){
        sectionDAO.clearAllSectionsFromPortfolio(idPortfolio)
    }

    suspend fun clearSection(id: Long){
        sectionDAO.clearSection(id)
    }

    suspend fun deleteSection(id: Long){
        sectionDAO.deleteSection(id)
    }

    suspend fun recoverSection(id: Long){
        sectionDAO.recoverSection(id)
    }

    suspend fun deleteAllSections(){
        sectionDAO.deleteAllSections()
    }

    suspend fun deleteAllSectionsFromPortfolio(idPortfolio: Long){
        sectionDAO.deleteAllSectionsFromPortfolio(idPortfolio)
    }

    suspend fun recoverAllSectionsFromPortfolio(idPortfolio: Long){
        sectionDAO.recoverAllSectionsFromPortfolio(idPortfolio)
    }

    suspend fun getAllSectionsHistory(): LiveData<List<Section>>{
        val allSections = MutableLiveData<List<Section>>()
        allSections.value = sectionDAO.getAllHistorySections()
        return allSections
    }

    suspend fun getSectionsHistoryWithAssets(): LiveData<List<SectionWithAssets>>{
        val allSectionsWithAssets = MutableLiveData<List<SectionWithAssets>>()
        allSectionsWithAssets.value = sectionDAO.getSectionsHistoryWithAssets()
        return allSectionsWithAssets
    }

    suspend fun getSectionsWithAssets(): LiveData<List<SectionWithAssets>>{
        val allSectionsWithAssets = MutableLiveData<List<SectionWithAssets>>()
        allSectionsWithAssets.value = sectionDAO.getSectionsWithAssets()
        return allSectionsWithAssets
    }

    suspend fun getSectionWithAssets(id: Long): LiveData<SectionWithAssets>{
        val sectionWithAssets = MutableLiveData<SectionWithAssets>()
        sectionWithAssets.value = sectionDAO.getSectionWithAssets(id)
        return sectionWithAssets
    }

    suspend fun getAllSections(): LiveData<List<Section>>{
        val allSections = MutableLiveData<List<Section>>()
        allSections.value = sectionDAO.getAllSections()
        return allSections
    }

    suspend fun getSection(id: Long): LiveData<Section>{
        val section = MutableLiveData<Section>()
        section.value = sectionDAO.getSection(id)
        return section
    }

    suspend fun getAllSectionsFromPortfolio(idPortfolio: Long): LiveData<List<Section>>{
        val allSectionsFromPortfolio = MutableLiveData<List<Section>>()
        allSectionsFromPortfolio.value = sectionDAO.getAllSectionsFromPortfolio(idPortfolio)
        return allSectionsFromPortfolio
    }

    suspend fun getAllSectionsFromPortfolioHistory(idPortfolio: Long): LiveData<List<Section>>{
        val allSectionsFromPortfolioHistory = MutableLiveData<List<Section>>()
        allSectionsFromPortfolioHistory.value =
                sectionDAO.getAllSectionsFromPortfolioHistory(idPortfolio)
        return allSectionsFromPortfolioHistory
    }

    //----------------------------------------------------------------------Deposit methods
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

    suspend fun deleteAllDeposits(){
        depositDAO.deleteAllDeposits()
    }

    suspend fun deleteAllDepositsFromPortfolio(idPortfolio: Long){
        depositDAO.deleteAllDepositsFromPortfolio(idPortfolio)
    }

    suspend fun recoverAllDepositsFromPortfolio(idPortfolio: Long){
        depositDAO.recoverAllDepositsFromPortfolio(idPortfolio)
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

    suspend fun recoverApp(id: Long){
        appDAO.recoverApp(id)
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

    suspend fun getAppsHistoryWithAssets(): LiveData<List<AppWithAssets>>{
        val allAppsWithAssets = MutableLiveData<List<AppWithAssets>>()
        allAppsWithAssets.value = appDAO.getAppsHistoryWithAssets()
        return allAppsWithAssets
    }

    suspend fun getAppsWithAssets(): LiveData<List<AppWithAssets>>{
        val allAppsWithAssets = MutableLiveData<List<AppWithAssets>>()
        allAppsWithAssets.value = appDAO.getAppsWithAssets()
        return allAppsWithAssets
    }

    suspend fun getAppWithAssets(id: Long): LiveData<AppWithAssets>{
        val appWithAssets = MutableLiveData<AppWithAssets>()
        appWithAssets.value = appDAO.getAppWithAssets(id)
        return appWithAssets
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

    suspend fun recoverPortfolio(id: Long){
        portfolioDAO.recoverPortfolio(id)
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

    suspend fun getAllPortfoliosWithSections(): LiveData<List<PortfolioWithSections>>{
        val allPortfoliosWithSections = MutableLiveData<List<PortfolioWithSections>>()
        allPortfoliosWithSections.value = portfolioDAO.getPortfoliosWithSections()
        return allPortfoliosWithSections
    }

    suspend fun getPortfolioWithSections(id: Long): LiveData<PortfolioWithSections>{
        val portfolioWithSections = MutableLiveData<PortfolioWithSections>()
        portfolioWithSections.value = portfolioDAO.getPortfolioWithSections(id)
        return portfolioWithSections
    }

    companion object{
        @Volatile private var instance: SIPManagerRepository? = null

        fun getInstance(
                portfolioDAO: PortfolioDAO,
                depositDAO: DepositDAO,
                sectionDAO: SectionDAO,
                assetDAO: AssetDAO,
                appDAO: AppDAO) =
                instance ?: synchronized(this){
                    instance ?: SIPManagerRepository(
                            portfolioDAO,
                            depositDAO,
                            sectionDAO,
                            assetDAO,
                            appDAO).also { instance = it }
                }
    }

}