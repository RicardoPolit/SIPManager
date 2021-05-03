package com.github.ricardopolit.sipmanager.data.assetbuyvi

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AssetBuyVIDAO {

    @Insert
    suspend fun insert(assetBuyVI: AssetBuyVI)

    @Update
    suspend fun update(assetBuyVI: AssetBuyVI)

    @Query("SELECT * FROM assets_buy_variableincome WHERE id_asset_buy_vi = :id")
    suspend fun getAssetBuyVI(id: Long): AssetBuyVI?

    @Query("SELECT * FROM assets_buy_variableincome WHERE active = 1 ORDER BY date_bought")
    fun getAllAssetBuyVI(): LiveData<List<AssetBuyVI>>

    @Query("SELECT * FROM assets_buy_variableincome ORDER BY date_bought")
    fun getAllAssetBuyVIHistory(): LiveData<List<AssetBuyVI>>

    @Query("SELECT * FROM assets_buy_variableincome WHERE id_foreign_asset = :idAsset AND active = 1 ORDER BY date_bought")
    fun getAllAssetBuyVIFromAsset(idAsset: Long): LiveData<List<AssetBuyVI>>

    @Query("SELECT * FROM assets_buy_variableincome WHERE id_foreign_asset = :idAsset ORDER BY date_bought")
    fun getAllAssetBuyVIHistoryFromAsset(idAsset: Long): LiveData<List<AssetBuyVI>>

    @Query("UPDATE assets_buy_variableincome SET active = 0 WHERE id_asset_buy_vi = :id")
    suspend fun inactiveAssetBuyVI( id: Long )

    @Query("UPDATE assets_buy_variableincome SET active = 1 WHERE id_asset_buy_vi = :id")
    suspend fun activeAssetBuyVI( id: Long )

    @Query("UPDATE assets_buy_variableincome SET active = 0 WHERE id_foreign_asset = :idAsset")
    suspend fun inactiveAllAssetBuyVIFromAsset( idAsset: Long )

    @Query("UPDATE assets_buy_variableincome SET active = 0")
    suspend fun inactiveAllAssetBuyVI()

    @Query("DELETE FROM assets_buy_variableincome WHERE id_asset_buy_vi = :id")
    suspend fun clearAssetBuyVI(id: Long)

    @Query("DELETE FROM assets_buy_variableincome")
    suspend fun clearAllAssetsBuyVI()

    @Query("DELETE FROM assets_buy_variableincome WHERE id_foreign_asset = :idAsset")
    suspend fun clearAllAssetAssetsBuyVIFromAsset(idAsset: Long)

}