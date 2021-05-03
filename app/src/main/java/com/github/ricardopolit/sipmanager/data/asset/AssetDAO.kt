package com.github.ricardopolit.sipmanager.data.asset

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AssetDAO {

    @Insert
    suspend fun insert(asset: Asset)

    @Update
    suspend fun update(asset: Asset)

    @Query("DELETE FROM assets")
    suspend fun clearAllAssets()

    @Query("DELETE FROM assets WHERE id_asset = :id")
    suspend fun clearAsset(id: Long)

    @Query("UPDATE assets SET active = 0 WHERE id_asset = :id")
    suspend fun deleteAsset(id: Long)

    @Query("UPDATE assets SET active = 1 WHERE id_asset = :id")
    suspend fun recoverAsset(id: Long)

    @Query("SELECT * FROM assets ORDER BY id_asset")
    fun getAllHistoryAssets(): LiveData<List<Asset>>

    @Query("SELECT * FROM assets WHERE active = 1 ORDER BY id_asset")
    fun getAllAssets(): LiveData<List<Asset>>

    @Query("SELECT * FROM assets WHERE id_asset = :id")
    suspend fun getAsset(id: Long): Asset?

    @Transaction
    @Query( "SELECT * FROM assets")
    fun getAssetsHistoryWithSections(): LiveData<List<AssetWithSections>>

    @Transaction
    @Query( "SELECT * FROM assets WHERE active = 1")
    fun getAssetsWithSections(): LiveData<List<AssetWithSections>>

    @Transaction
    @Query("SELECT * FROM assets WHERE id_asset = :id")
    suspend fun getAssetWithSections(id: Long): AssetWithSections?

    @Transaction
    @Query( "SELECT * FROM assets")
    fun getAssetsHistoryWithApps(): LiveData<List<AssetWithApps>>

    @Transaction
    @Query( "SELECT * FROM assets WHERE active = 1")
    fun getAssetsWithApps(): LiveData<List<AssetWithApps>>

    @Transaction
    @Query("SELECT * FROM assets WHERE id_asset = :id")
    suspend fun getAssetWithApps(id: Long): AssetWithApps?

    @Transaction
    @Query( "SELECT * FROM assets")
    fun getAssetsHistoryWithAssetHistoryValues(): LiveData<List<AssetWithAssetValueHistory>>

    @Transaction
    @Query( "SELECT * FROM assets WHERE active = 1")
    fun getAssetsWithAssetHistoryValues(): LiveData<List<AssetWithAssetValueHistory>>

    @Transaction
    @Query("SELECT * FROM assets WHERE id_asset = :id")
    suspend fun getAssetWithAssetHistoryValues(id: Long): AssetWithAssetValueHistory?

    @Transaction
    @Query( "SELECT * FROM assets WHERE income_type = 1")
    fun getAssetsHistoryWithAssetBuyVIs(): LiveData<List<AssetWithAssetBuyVI>>

    @Transaction
    @Query( "SELECT * FROM assets WHERE active = 1 AND income_type = 1")
    fun getAssetsWithAssetBuyVIs(): LiveData<List<AssetWithAssetBuyVI>>

    @Transaction
    @Query("SELECT * FROM assets WHERE id_asset = :id AND income_type = 1")
    suspend fun getAssetWithAssetBuyVIs(id: Long): AssetWithAssetBuyVI?

    @Transaction
    @Query( "SELECT * FROM assets WHERE income_type = 1")
    fun getAssetsHistoryWithAssetDividendVIs(): LiveData<List<AssetWithAssetDividendVI>>

    @Transaction
    @Query( "SELECT * FROM assets WHERE active = 1 AND income_type = 1")
    fun getAssetsWithAssetDividendVIs(): LiveData<List<AssetWithAssetDividendVI>>

    @Transaction
    @Query("SELECT * FROM assets WHERE id_asset = :id AND income_type = 1")
    suspend fun getAssetWithAssetDividendVIs(id: Long): AssetWithAssetDividendVI?

    @Transaction
    @Query( "SELECT * FROM assets WHERE income_type = 0")
    fun getAssetsHistoryWithAssetBuyFIs(): LiveData<List<AssetWithAssetBuyFI>>

    @Transaction
    @Query( "SELECT * FROM assets WHERE active = 1 AND income_type = 0")
    fun getAssetsWithAssetBuyFIs(): LiveData<List<AssetWithAssetBuyFI>>

    @Transaction
    @Query("SELECT * FROM assets WHERE id_asset = :id AND income_type = 0")
    suspend fun getAssetWithAssetBuyFIs(id: Long): AssetWithAssetBuyFI?

}