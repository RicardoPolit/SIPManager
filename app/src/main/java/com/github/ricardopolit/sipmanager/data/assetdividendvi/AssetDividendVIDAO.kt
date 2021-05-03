package com.github.ricardopolit.sipmanager.data.assetdividendvi

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AssetDividendVIDAO {

    @Insert
    suspend fun insert(assetDividendVI: AssetDividendVI)

    @Update
    suspend fun update(assetDividendVI: AssetDividendVI)

    @Query("SELECT * FROM assets_dividend_variableincome WHERE id_asset_dividend_vi = :id")
    suspend fun getAssetDividendVI(id: Long): AssetDividendVI?

    @Query("SELECT * FROM assets_dividend_variableincome WHERE active = 1 ORDER BY date_returned")
    fun getAllAssetDividendVI(): LiveData<List<AssetDividendVI>>

    @Query("SELECT * FROM assets_dividend_variableincome ORDER BY date_returned")
    fun getAllAssetDividendVIHistory(): LiveData<List<AssetDividendVI>>

    @Query("SELECT * FROM assets_dividend_variableincome WHERE id_foreign_asset = :idAsset AND active = 1 ORDER BY date_returned")
    fun getAllAssetDividendVIFromAsset(idAsset: Long): LiveData<List<AssetDividendVI>>

    @Query("SELECT * FROM assets_dividend_variableincome WHERE id_foreign_asset = :idAsset ORDER BY date_returned")
    fun getAllAssetDividendVIHistoryFromAsset(idAsset: Long): LiveData<List<AssetDividendVI>>

    @Query("UPDATE assets_dividend_variableincome SET active = 0 WHERE id_asset_dividend_vi = :id")
    suspend fun inactiveAssetDividendVI( id: Long )

    @Query("UPDATE assets_dividend_variableincome SET active = 1 WHERE id_asset_dividend_vi = :id")
    suspend fun activeAssetDividendVI( id: Long )

    @Query("UPDATE assets_dividend_variableincome SET active = 0 WHERE id_foreign_asset = :idAsset")
    suspend fun inactiveAllAssetDividendVIFromAsset( idAsset: Long )

    @Query("UPDATE assets_dividend_variableincome SET active = 0")
    suspend fun inactiveAllAssetDividendVI()

    @Query("DELETE FROM assets_dividend_variableincome WHERE id_asset_dividend_vi = :id")
    suspend fun clearAssetDividendVI(id: Long)

    @Query("DELETE FROM assets_dividend_variableincome")
    suspend fun clearAllAssetsDividendVI()

    @Query("DELETE FROM assets_dividend_variableincome WHERE id_foreign_asset = :idAsset")
    suspend fun clearAllAssetAssetsDividendVIFromAsset(idAsset: Long)

}