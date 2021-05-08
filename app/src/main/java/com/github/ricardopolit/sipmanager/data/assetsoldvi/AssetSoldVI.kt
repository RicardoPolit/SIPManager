package com.github.ricardopolit.sipmanager.data.assetsoldvi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets_sold_variableincome")
data class AssetSoldVI(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_asset_sold_vi")
        var idAssetSoldVI: Long = 0L,

        @ColumnInfo(name = "id_foreign_asset")
        var idAsset: Long,

        @ColumnInfo(name = "value_sold")
        var valueSold: Float,

        @ColumnInfo(name = "quantity")
        var quantity: Float,

        @ColumnInfo(name = "date_sold")
        var dateSold: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "earnings")
        var earnings: Float,

        @ColumnInfo(name = "active")
        var active: Boolean = true

)
