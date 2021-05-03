package com.github.ricardopolit.sipmanager.data.assetsoldvi

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AssetSoldVIDAO {

    @Insert
    suspend fun insert(assetSoldVI: AssetSoldVI)

    @Update
    suspend fun update(assetSoldVI: AssetSoldVI)

    @Query("SELECT * FROM assets_sold_variableincome WHERE id_asset_sold_vi = :id")
    suspend fun getAssetSoldVI(id: Long): AssetSoldVI?

    @Query("SELECT * FROM assets_sold_variableincome WHERE active = 1 ORDER BY date_sold")
    fun getAllAssetSoldVI(): LiveData<List<AssetSoldVI>>

    @Query("SELECT * FROM assets_sold_variableincome ORDER BY date_sold")
    fun getAllAssetSoldVIHistory(): LiveData<List<AssetSoldVI>>

    @Query("SELECT * FROM assets_sold_variableincome WHERE id_foreign_asset = :idAsset AND active = 1 ORDER BY date_sold")
    fun getAllAssetSoldVIFromAsset(idAsset: Long): LiveData<List<AssetSoldVI>>

    @Query("SELECT * FROM assets_sold_variableincome WHERE id_foreign_asset = :idAsset ORDER BY date_sold")
    fun getAllAssetSoldVIHistoryFromAsset(idAsset: Long): LiveData<List<AssetSoldVI>>

    @Query("UPDATE assets_sold_variableincome SET active = 0 WHERE id_asset_sold_vi = :id")
    suspend fun inactiveAssetSoldVI( id: Long )

    @Query("UPDATE assets_sold_variableincome SET active = 1 WHERE id_asset_sold_vi = :id")
    suspend fun activeAssetSoldVI( id: Long )

    @Query("UPDATE assets_sold_variableincome SET active = 0 WHERE id_foreign_asset = :idAsset")
    suspend fun inactiveAllAssetSoldVIFromAsset( idAsset: Long )

    @Query("UPDATE assets_sold_variableincome SET active = 0")
    suspend fun inactiveAllAssetSoldVI()

    @Query("DELETE FROM assets_sold_variableincome WHERE id_asset_sold_vi = :id")
    suspend fun clearAssetSoldVI(id: Long)

    @Query("DELETE FROM assets_sold_variableincome")
    suspend fun clearAllAssetsSoldVI()

    @Query("DELETE FROM assets_sold_variableincome WHERE id_foreign_asset = :idAsset")
    suspend fun clearAllAssetAssetsSoldVIFromAsset(idAsset: Long)

}