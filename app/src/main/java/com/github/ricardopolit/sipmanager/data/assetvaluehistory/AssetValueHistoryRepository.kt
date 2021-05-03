package com.github.ricardopolit.sipmanager.data.assetvaluehistory

import androidx.lifecycle.LiveData
import com.github.ricardopolit.sipmanager.data.asset.AssetDAO

class AssetValueHistoryRepository private constructor(
        private val assetValueHistoryDAO: AssetValueHistoryDAO,
        private val assetDAO: AssetDAO){

    companion object{
        @Volatile private var instance: AssetValueHistoryRepository? = null

        fun getInstance(assetValueHistoryDAO: AssetValueHistoryDAO,
                        assetDAO: AssetDAO) =
                instance ?: synchronized(this){
                    instance ?: AssetValueHistoryRepository(assetValueHistoryDAO,
                                                            assetDAO).also { instance = it }
                }

    }

    suspend fun insertAssetHistoryValue(assetValueHistory: AssetValueHistory ){

        if( assetDAO.getAsset(assetValueHistory.idAsset) != null )
            assetValueHistoryDAO.insert(assetValueHistory)
        else
            error("AssetHistoryValue insertion error, Asset not found")

    }

    suspend fun updateAssetHistoryValue(assetValueHistory: AssetValueHistory ){
        assetValueHistoryDAO.update(assetValueHistory)
    }

    fun getAllAssetsValuesHistory(): LiveData<List<AssetValueHistory>>{
        return assetValueHistoryDAO.getAllAssetsValuesHistory()
    }

    suspend fun getAssetValueHistory(id: Long): AssetValueHistory?{
        return assetValueHistoryDAO.getAssetValueHistory(id)
    }

    fun getAllAssetValuesHistoryFromAsset(idAsset: Long): LiveData<List<AssetValueHistory>>{
        return assetValueHistoryDAO.getAllAssetValuesHistoryFromAsset(idAsset)
    }

    suspend fun clearAssetValueHistory(id: Long){
        assetValueHistoryDAO.clearAssetValueHistory(id)
    }

    suspend fun clearAllAssetsValuesHistory(){
        assetValueHistoryDAO.clearAllAssetsValuesHistory()
    }

    suspend fun clearAllAssetValuesHistoryFromAsset(idAsset: Long){
        assetValueHistoryDAO.clearAllAssetValuesHistoryFromAsset(idAsset)
    }

}