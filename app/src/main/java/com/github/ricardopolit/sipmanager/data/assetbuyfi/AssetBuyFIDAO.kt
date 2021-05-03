package com.github.ricardopolit.sipmanager.data.assetbuyfi

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AssetBuyFIDAO {

    @Insert
    suspend fun insert(assetBuyFI: AssetBuyFI)

    @Update
    suspend fun update(assetBuyFI: AssetBuyFI)

    @Query("SELECT * FROM assets_buy_fixedincome WHERE id_asset_buy_fi = :id")
    suspend fun getAssetBuyFI(id: Long): AssetBuyFI?

    @Query("SELECT * FROM assets_buy_fixedincome WHERE active = 1 ORDER BY date_bought")
    fun getAllAssetBuyFI(): LiveData<List<AssetBuyFI>>

    @Query("SELECT * FROM assets_buy_fixedincome ORDER BY date_bought")
    fun getAllAssetBuyFIHistory(): LiveData<List<AssetBuyFI>>

    @Query("SELECT * FROM assets_buy_fixedincome WHERE id_foreign_asset = :idAsset AND active = 1 ORDER BY date_bought")
    fun getAllAssetBuyFIFromAsset(idAsset: Long): LiveData<List<AssetBuyFI>>

    @Query("SELECT * FROM assets_buy_fixedincome WHERE id_foreign_asset = :idAsset ORDER BY date_bought")
    fun getAllAssetBuyFIHistoryFromAsset(idAsset: Long): LiveData<List<AssetBuyFI>>

    @Query("UPDATE assets_buy_fixedincome SET active = 0 WHERE id_asset_buy_fi = :id")
    suspend fun inactiveAssetBuyFI( id: Long )

    @Query("UPDATE assets_buy_fixedincome SET active = 1 WHERE id_asset_buy_fi = :id")
    suspend fun activeAssetBuyFI( id: Long )

    @Query("UPDATE assets_buy_fixedincome SET active = 0 WHERE id_foreign_asset = :idAsset")
    suspend fun inactiveAllAssetBuyFIFromAsset( idAsset: Long )

    @Query("UPDATE assets_buy_fixedincome SET active = 0")
    suspend fun inactiveAllAssetBuyFI()

    @Query("DELETE FROM assets_buy_fixedincome WHERE id_asset_buy_fi = :id")
    suspend fun clearAssetBuyFI(id: Long)

    @Query("DELETE FROM assets_buy_fixedincome")
    suspend fun clearAllAssetsBuyFI()

    @Query("DELETE FROM assets_buy_fixedincome WHERE id_foreign_asset = :idAsset")
    suspend fun clearAllAssetAssetsBuyFIFromAsset(idAsset: Long)

}