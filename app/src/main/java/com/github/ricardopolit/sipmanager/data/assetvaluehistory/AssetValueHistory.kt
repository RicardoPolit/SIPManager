package com.github.ricardopolit.sipmanager.data.assetvaluehistory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asset_history_values")
data class AssetValueHistory(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_asset_history_value")
        var id: Long = 0L,

        @ColumnInfo(name = "id_foreign_asset")
        var idAsset: Long,

        @ColumnInfo(name = "value")
        var value: Float,

        @ColumnInfo(name = "date")
        var date: Long = System.currentTimeMillis()

)
