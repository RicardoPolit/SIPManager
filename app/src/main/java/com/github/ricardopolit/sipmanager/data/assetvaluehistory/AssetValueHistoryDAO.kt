package com.github.ricardopolit.sipmanager.data.assetvaluehistory

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AssetValueHistoryDAO {

    @Insert
    suspend fun insert(assetValueHistory: AssetValueHistory)

    @Update
    suspend fun update(assetValueHistory: AssetValueHistory)

    @Query( "SELECT * FROM asset_history_values ORDER BY date" )
    fun getAllAssetsValuesHistory(): LiveData<List<AssetValueHistory>>

    @Query( "SELECT * FROM asset_history_values WHERE id_asset_history_value = :id" )
    suspend fun getAssetValueHistory(id: Long): AssetValueHistory?

    @Query("SELECT * FROM asset_history_values WHERE id_foreign_asset = :idAsset ORDER BY date")
    fun getAllAssetValuesHistoryFromAsset(idAsset: Long): LiveData<List<AssetValueHistory>>

    @Query("DELETE FROM asset_history_values WHERE id_asset_history_value = :id")
    suspend fun clearAssetValueHistory(id: Long)

    @Query("DELETE FROM asset_history_values")
    suspend fun clearAllAssetsValuesHistory()

    @Query("DELETE FROM asset_history_values WHERE id_foreign_asset = :idAsset")
    suspend fun clearAllAssetValuesHistoryFromAsset(idAsset: Long)

}