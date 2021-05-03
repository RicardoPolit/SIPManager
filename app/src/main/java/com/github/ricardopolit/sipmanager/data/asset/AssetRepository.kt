package com.github.ricardopolit.sipmanager.data.asset

import androidx.lifecycle.LiveData

class AssetRepository private constructor(
        private val assetDAO: AssetDAO){

    companion object{
        @Volatile private var instance: AssetRepository? = null

        fun getInstance(assetDAO: AssetDAO) =
                instance ?: synchronized(this){
                    instance ?: AssetRepository(assetDAO).also { instance = it }
                }
    }

    suspend fun insertAsset(asset: Asset){
        assetDAO.insert(asset)
    }

    suspend fun updateAsset(asset: Asset){
        assetDAO.update(asset)
    }

    suspend fun clearAllAssets(){
        assetDAO.clearAllAssets()
    }

    suspend fun clearAsset(id: Long){
        assetDAO.clearAsset(id)
    }

    suspend fun deleteAsset(id: Long){
        assetDAO.deleteAsset(id)
    }

    suspend fun recoverAsset(id: Long){
        assetDAO.recoverAsset(id)
    }

    fun getAllHistoryAssets(): LiveData<List<Asset>>{
        return assetDAO.getAllHistoryAssets()
    }

    fun getAllAssets(): LiveData<List<Asset>>{
        return assetDAO.getAllAssets()
    }

    suspend fun getAsset(id: Long): Asset?{
        return assetDAO.getAsset(id)
    }

    fun getAssetsHistoryWithSections(): LiveData<List<AssetWithSections>>{
        return assetDAO.getAssetsHistoryWithSections()
    }

    fun getAssetsWithSections(): LiveData<List<AssetWithSections>>{
        return assetDAO.getAssetsWithSections()
    }

    fun getAssetsHistoryWithApps(): LiveData<List<AssetWithApps>>{
        return assetDAO.getAssetsHistoryWithApps()
    }

    fun getAssetsWithApps(): LiveData<List<AssetWithApps>>{
        return assetDAO.getAssetsWithApps()
    }

    suspend fun getAssetWithApps(id: Long): AssetWithApps?{
        return assetDAO.getAssetWithApps(id)
    }

    fun getAssetsHistoryWithAssetHistoryValues(): LiveData<List<AssetWithAssetValueHistory>>{
        return assetDAO.getAssetsHistoryWithAssetHistoryValues()
    }

    fun getAssetsWithAssetHistoryValues(): LiveData<List<AssetWithAssetValueHistory>>{
        return assetDAO.getAssetsWithAssetHistoryValues()
    }

    suspend fun getAssetWithAssetHistoryValues(id: Long): AssetWithAssetValueHistory?{
        return assetDAO.getAssetWithAssetHistoryValues(id)
    }

    fun getAssetsHistoryWithAssetBuyVIs(): LiveData<List<AssetWithAssetBuyVI>>{
        return assetDAO.getAssetsHistoryWithAssetBuyVIs()
    }

    fun getAssetsWithAssetBuyVIs(): LiveData<List<AssetWithAssetBuyVI>>{
        return assetDAO.getAssetsWithAssetBuyVIs()
    }

    suspend fun getAssetWithAssetBuyVIs(id: Long): AssetWithAssetBuyVI?{
        return assetDAO.getAssetWithAssetBuyVIs(id)
    }

    fun getAssetsHistoryWithAssetDividendVIs(): LiveData<List<AssetWithAssetDividendVI>>{
        return assetDAO.getAssetsHistoryWithAssetDividendVIs()
    }

    fun getAssetsWithAssetDividendVIs(): LiveData<List<AssetWithAssetDividendVI>>{
        return assetDAO.getAssetsWithAssetDividendVIs()
    }

    suspend fun getAssetWithAssetDividendVIs(id: Long): AssetWithAssetDividendVI?{
        return assetDAO.getAssetWithAssetDividendVIs(id)
    }

    fun getAssetsHistoryWithAssetBuyFIs(): LiveData<List<AssetWithAssetBuyFI>>{
        return assetDAO.getAssetsHistoryWithAssetBuyFIs()
    }

    fun getAssetsWithAssetBuyFIs(): LiveData<List<AssetWithAssetBuyFI>>{
        return assetDAO.getAssetsWithAssetBuyFIs()
    }

    suspend fun getAssetWithAssetBuyFIs(id: Long): AssetWithAssetBuyFI?{
        return assetDAO.getAssetWithAssetBuyFIs(id)
    }

}