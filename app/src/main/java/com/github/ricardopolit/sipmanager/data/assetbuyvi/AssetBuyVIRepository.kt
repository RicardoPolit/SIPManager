package com.github.ricardopolit.sipmanager.data.assetbuyvi

import androidx.lifecycle.LiveData
import com.github.ricardopolit.sipmanager.data.asset.AssetDAO


class AssetBuyVIRepository private constructor(
        private val assetBuyVIDAO: AssetBuyVIDAO,
        private val assetDAO: AssetDAO){

    companion object{
        @Volatile private var instance : AssetBuyVIRepository? = null

        fun getInstance(
                assetBuyVIDAO: AssetBuyVIDAO,
                assetDAO: AssetDAO) =
                instance ?: synchronized(this){
                    instance ?: AssetBuyVIRepository(assetBuyVIDAO,assetDAO)
                }

    }

    suspend fun insertAssetBuyVI(assetBuyVI: AssetBuyVI){

        if(assetDAO.getAsset(assetBuyVI.idAsset) != null)
            assetBuyVIDAO.insert(assetBuyVI)
        else
            error("AssetBuyVI insertion error, Asset not found")
    }

    suspend fun updateAssetBuyVI(assetBuyVI: AssetBuyVI){
        assetBuyVIDAO.update(assetBuyVI)
    }

    suspend fun getAssetBuyVI(id: Long): AssetBuyVI?{
        return assetBuyVIDAO.getAssetBuyVI(id)
    }

    fun getAllAssetBuyVI(): LiveData<List<AssetBuyVI>>{
        return assetBuyVIDAO.getAllAssetBuyVI()
    }

    fun getAllAssetBuyVIHistory(): LiveData<List<AssetBuyVI>>{
        return assetBuyVIDAO.getAllAssetBuyVIHistory()
    }

    fun getAllAssetBuyVIFromAsset(idAsset: Long): LiveData<List<AssetBuyVI>>{
        return assetBuyVIDAO.getAllAssetBuyVIFromAsset(idAsset)
    }

    fun getAllAssetBuyVIHistoryFromAsset(idAsset: Long): LiveData<List<AssetBuyVI>>{
        return assetBuyVIDAO.getAllAssetBuyVIHistoryFromAsset(idAsset)
    }

    suspend fun inactiveAssetBuyVI( id: Long ){
        assetBuyVIDAO.inactiveAssetBuyVI(id)
    }

    suspend fun activeAssetBuyVI( id: Long ){
        assetBuyVIDAO.activeAssetBuyVI(id)
    }

    suspend fun inactiveAllAssetBuyVIFromAsset( idAsset: Long ){
        assetBuyVIDAO.inactiveAllAssetBuyVIFromAsset(idAsset)
    }

    suspend fun inactiveAllAssetBuyVI(){
        assetBuyVIDAO.inactiveAllAssetBuyVI()
    }

    suspend fun clearAssetBuyVI(id: Long){
        assetBuyVIDAO.clearAssetBuyVI(id)
    }

    suspend fun clearAllAssetsBuyVI(){
        assetBuyVIDAO.clearAllAssetsBuyVI()
    }

    suspend fun clearAllAssetAssetsBuyVIFromAsset(idAsset: Long){
        assetBuyVIDAO.clearAllAssetAssetsBuyVIFromAsset(idAsset)
    }

}