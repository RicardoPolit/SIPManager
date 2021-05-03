package com.github.ricardopolit.sipmanager.data.assetdividendvi

import androidx.lifecycle.LiveData
import com.github.ricardopolit.sipmanager.data.asset.AssetDAO


class AssetDividendVIRepository private constructor(
        private val assetDividendVIDAO: AssetDividendVIDAO,
        private val assetDAO: AssetDAO){

    companion object{
        @Volatile private var instance : AssetDividendVIRepository? = null

        fun getInstance(
                assetDividendVIDAO: AssetDividendVIDAO,
                assetDAO: AssetDAO) =
                instance ?: synchronized(this){
                    instance ?: AssetDividendVIRepository(assetDividendVIDAO,assetDAO)
                }

    }

    suspend fun insertAssetDividendVI(assetDividendVI: AssetDividendVI){

        if(assetDAO.getAsset(assetDividendVI.idAsset) != null)
            assetDividendVIDAO.insert(assetDividendVI)
        else
            error("AssetDividendVI insertion error, Asset not found")
    }

    suspend fun updateAssetDividendVI(assetDividendVI: AssetDividendVI){
        assetDividendVIDAO.update(assetDividendVI)
    }

    suspend fun getAssetDividendVI(id: Long): AssetDividendVI?{
        return assetDividendVIDAO.getAssetDividendVI(id)
    }

    fun getAllAssetDividendVI(): LiveData<List<AssetDividendVI>>{
        return assetDividendVIDAO.getAllAssetDividendVI()
    }

    fun getAllAssetDividendVIHistory(): LiveData<List<AssetDividendVI>>{
        return assetDividendVIDAO.getAllAssetDividendVIHistory()
    }

    fun getAllAssetDividendVIFromAsset(idAsset: Long): LiveData<List<AssetDividendVI>>{
        return assetDividendVIDAO.getAllAssetDividendVIFromAsset(idAsset)
    }

    fun getAllAssetDividendVIHistoryFromAsset(idAsset: Long): LiveData<List<AssetDividendVI>>{
        return assetDividendVIDAO.getAllAssetDividendVIHistoryFromAsset(idAsset)
    }

    suspend fun inactiveAssetDividendVI( id: Long ){
        assetDividendVIDAO.inactiveAssetDividendVI(id)
    }

    suspend fun activeAssetDividendVI( id: Long ){
        assetDividendVIDAO.activeAssetDividendVI(id)
    }

    suspend fun inactiveAllAssetDividendVIFromAsset( idAsset: Long ){
        assetDividendVIDAO.inactiveAllAssetDividendVIFromAsset(idAsset)
    }

    suspend fun inactiveAllAssetDividendVI(){
        assetDividendVIDAO.inactiveAllAssetDividendVI()
    }

    suspend fun clearAssetDividendVI(id: Long){
        assetDividendVIDAO.clearAssetDividendVI(id)
    }

    suspend fun clearAllAssetsDividendVI(){
        assetDividendVIDAO.clearAllAssetsDividendVI()
    }

    suspend fun clearAllAssetAssetsDividendVIFromAsset(idAsset: Long){
        assetDividendVIDAO.clearAllAssetAssetsDividendVIFromAsset(idAsset)
    }

}