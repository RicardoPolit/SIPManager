package com.github.ricardopolit.sipmanager.data.assetsoldvi

import androidx.lifecycle.LiveData
import com.github.ricardopolit.sipmanager.data.asset.AssetDAO


class AssetSoldVIRepository private constructor(
        private val assetSoldVIDAO: AssetSoldVIDAO,
        private val assetDAO: AssetDAO){

    companion object{
        @Volatile private var instance : AssetSoldVIRepository? = null

        fun getInstance(
                assetSoldVIDAO: AssetSoldVIDAO,
                assetDAO: AssetDAO) =
                instance ?: synchronized(this){
                    instance ?: AssetSoldVIRepository(assetSoldVIDAO,assetDAO)
                }

    }

    suspend fun insertAssetSoldVI(assetSoldVI: AssetSoldVI){

        if(assetDAO.getAsset(assetSoldVI.idAsset) != null)
            assetSoldVIDAO.insert(assetSoldVI)
        else
            error("AssetSoldVI insertion error, Asset not found")
    }

    suspend fun updateAssetSoldVI(assetSoldVI: AssetSoldVI){
        assetSoldVIDAO.update(assetSoldVI)
    }

    suspend fun getAssetSoldVI(id: Long): AssetSoldVI?{
        return assetSoldVIDAO.getAssetSoldVI(id)
    }

    fun getAllAssetSoldVI(): LiveData<List<AssetSoldVI>>{
        return assetSoldVIDAO.getAllAssetSoldVI()
    }

    fun getAllAssetSoldVIHistory(): LiveData<List<AssetSoldVI>>{
        return assetSoldVIDAO.getAllAssetSoldVIHistory()
    }

    fun getAllAssetSoldVIFromAsset(idAsset: Long): LiveData<List<AssetSoldVI>>{
        return assetSoldVIDAO.getAllAssetSoldVIFromAsset(idAsset)
    }

    fun getAllAssetSoldVIHistoryFromAsset(idAsset: Long): LiveData<List<AssetSoldVI>>{
        return assetSoldVIDAO.getAllAssetSoldVIHistoryFromAsset(idAsset)
    }

    suspend fun inactiveAssetSoldVI( id: Long ){
        assetSoldVIDAO.inactiveAssetSoldVI(id)
    }

    suspend fun activeAssetSoldVI( id: Long ){
        assetSoldVIDAO.activeAssetSoldVI(id)
    }

    suspend fun inactiveAllAssetSoldVIFromAsset( idAsset: Long ){
        assetSoldVIDAO.inactiveAllAssetSoldVIFromAsset(idAsset)
    }

    suspend fun inactiveAllAssetSoldVI(){
        assetSoldVIDAO.inactiveAllAssetSoldVI()
    }

    suspend fun clearAssetSoldVI(id: Long){
        assetSoldVIDAO.clearAssetSoldVI(id)
    }

    suspend fun clearAllAssetsSoldVI(){
        assetSoldVIDAO.clearAllAssetsSoldVI()
    }

    suspend fun clearAllAssetAssetsSoldVIFromAsset(idAsset: Long){
        assetSoldVIDAO.clearAllAssetAssetsSoldVIFromAsset(idAsset)
    }

}