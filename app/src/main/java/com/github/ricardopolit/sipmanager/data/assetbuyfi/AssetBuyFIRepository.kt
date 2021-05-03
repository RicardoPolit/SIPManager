package com.github.ricardopolit.sipmanager.data.assetbuyfi

import androidx.lifecycle.LiveData
import com.github.ricardopolit.sipmanager.data.asset.AssetDAO


class AssetBuyFIRepository private constructor(
        private val assetBuyFIDAO: AssetBuyFIDAO,
        private val assetDAO: AssetDAO){

    companion object{
        @Volatile private var instance : AssetBuyFIRepository? = null

        fun getInstance(
                assetBuyFIDAO: AssetBuyFIDAO,
                assetDAO: AssetDAO) =
                instance ?: synchronized(this){
                    instance ?: AssetBuyFIRepository(assetBuyFIDAO,assetDAO)
                }

    }

    suspend fun insertAssetBuyFI(assetBuyFI: AssetBuyFI){

        if(assetDAO.getAsset(assetBuyFI.idAsset) != null)
            assetBuyFIDAO.insert(assetBuyFI)
        else
            error("AssetBuyFI insertion error, Asset not found")
    }

    suspend fun updateAssetBuyFI(assetBuyFI: AssetBuyFI){
        assetBuyFIDAO.update(assetBuyFI)
    }

    suspend fun getAssetBuyFI(id: Long): AssetBuyFI?{
        return assetBuyFIDAO.getAssetBuyFI(id)
    }

    fun getAllAssetBuyFI(): LiveData<List<AssetBuyFI>>{
        return assetBuyFIDAO.getAllAssetBuyFI()
    }

    fun getAllAssetBuyFIHistory(): LiveData<List<AssetBuyFI>>{
        return assetBuyFIDAO.getAllAssetBuyFIHistory()
    }

    fun getAllAssetBuyFIFromAsset(idAsset: Long): LiveData<List<AssetBuyFI>>{
        return assetBuyFIDAO.getAllAssetBuyFIFromAsset(idAsset)
    }

    fun getAllAssetBuyFIHistoryFromAsset(idAsset: Long): LiveData<List<AssetBuyFI>>{
        return assetBuyFIDAO.getAllAssetBuyFIHistoryFromAsset(idAsset)
    }

    suspend fun inactiveAssetBuyFI( id: Long ){
        assetBuyFIDAO.inactiveAssetBuyFI(id)
    }

    suspend fun activeAssetBuyFI( id: Long ){
        assetBuyFIDAO.activeAssetBuyFI(id)
    }

    suspend fun inactiveAllAssetBuyFIFromAsset( idAsset: Long ){
        assetBuyFIDAO.inactiveAllAssetBuyFIFromAsset(idAsset)
    }

    suspend fun inactiveAllAssetBuyFI(){
        assetBuyFIDAO.inactiveAllAssetBuyFI()
    }

    suspend fun clearAssetBuyFI(id: Long){
        assetBuyFIDAO.clearAssetBuyFI(id)
    }

    suspend fun clearAllAssetsBuyFI(){
        assetBuyFIDAO.clearAllAssetsBuyFI()
    }

    suspend fun clearAllAssetAssetsBuyFIFromAsset(idAsset: Long){
        assetBuyFIDAO.clearAllAssetAssetsBuyFIFromAsset(idAsset)
    }

}