package com.github.ricardopolit.sipmanager.data.assetbuyvi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets_buy_variableincome")
data class AssetBuyVI(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_asset_buy_vi")
        var idAssetBuyVI: Long = 0L,

        @ColumnInfo(name = "id_foreign_asset")
        var idAsset: Long,

        @ColumnInfo(name = "value_bought")
        var valueBought: Float,

        @ColumnInfo(name = "quantity")
        var quantity: Float,

        @ColumnInfo(name = "date_bought")
        var dateBought: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "active")
        var active: Boolean = true

)
