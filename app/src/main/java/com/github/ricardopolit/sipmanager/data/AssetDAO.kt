package com.github.ricardopolit.sipmanager.data

import androidx.room.*

//TODO implement functions in repository

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
    suspend fun getAllHistoryAssets(): List<Asset>

    @Query("SELECT * FROM assets WHERE active = 1 ORDER BY id_asset")
    suspend fun getAllAssets(): List<Asset>

    @Query("SELECT * FROM assets WHERE id_asset = :id")
    suspend fun getAsset(id: Long): Asset?

    @Transaction
    @Query( "SELECT * FROM assets")
    suspend fun getAssetsHistoryWithSections(): List<AssetWithSections>

    @Transaction
    @Query( "SELECT * FROM assets WHERE active = 1")
    suspend fun getAssetsWithSections(): List<AssetWithSections>

    @Transaction
    @Query("SELECT * FROM assets WHERE id_asset = :id")
    suspend fun getAssetWithSections(id: Long): AssetWithSections?

    @Transaction
    @Query( "SELECT * FROM assets")
    suspend fun getAssetsHistoryWithApps(): List<AssetWithApps>

    @Transaction
    @Query( "SELECT * FROM assets WHERE active = 1")
    suspend fun getAssetsWithApps(): List<AssetWithApps>

    @Transaction
    @Query("SELECT * FROM assets WHERE id_asset = :id")
    suspend fun getAssetWithApps(id: Long): AssetWithApps?
}