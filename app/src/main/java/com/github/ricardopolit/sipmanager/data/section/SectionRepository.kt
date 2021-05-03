package com.github.ricardopolit.sipmanager.data.section

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.ricardopolit.sipmanager.data.portfolio.PortfolioDAO

class SectionRepository private constructor(
        private val sectionDAO: SectionDAO,
        private val portfolioDAO: PortfolioDAO){

    companion object {
        @Volatile
        private var instance: SectionRepository? = null

        fun getInstance(sectionDAO: SectionDAO,
                        portfolioDAO: PortfolioDAO) =
                instance ?: synchronized(this) {
                    instance ?: SectionRepository(sectionDAO,
                            portfolioDAO).also { instance = it }
                }
    }

    suspend fun insertSection(section: Section){

        if(portfolioDAO.getPortfolio(section.idPortfolio)!=null)
            sectionDAO.insert(section)
        else
            error("Section insertion error, Portfolio not found")
    }

    suspend fun insertSectionAssetCrossRef(idSection: Long, idAsset: Long){
        val join = SectionAssetCrossRef(idSection,idAsset)
        sectionDAO.insertSectionAssetCrossRef(join)
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

    fun getAllSectionsHistory(): LiveData<List<Section>> {
        return sectionDAO.getAllHistorySections()
    }

    fun getSectionsHistoryWithAssets(): LiveData<List<SectionWithAssets>> {
        return sectionDAO.getSectionsHistoryWithAssets()
    }

    fun getSectionsWithAssets(): LiveData<List<SectionWithAssets>> {
        return sectionDAO.getSectionsWithAssets()
    }

    suspend fun getSectionWithAssets(id: Long): SectionWithAssets? {
        return sectionDAO.getSectionWithAssets(id)
    }

    fun getAllSections(): LiveData<List<Section>> {
        return sectionDAO.getAllSections()
    }

    suspend fun getSection(id: Long): Section? {
        return sectionDAO.getSection(id)
    }

    fun getAllSectionsFromPortfolio(idPortfolio: Long): LiveData<List<Section>> {
        return sectionDAO.getAllSectionsFromPortfolio(idPortfolio)
    }

    fun getAllSectionsFromPortfolioHistory(idPortfolio: Long): LiveData<List<Section>> {
        return sectionDAO.getAllSectionsFromPortfolioHistory(idPortfolio)
    }

}