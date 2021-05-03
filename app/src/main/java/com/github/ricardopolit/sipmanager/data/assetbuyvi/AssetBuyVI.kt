package com.github.ricardopolit.sipmanager.data.assetbuyvi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets_buy_variableincome")
data class AssetBuyVI(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_asset_buy_vi")
        val idAssetBuyVI: Long = 0L,

        @ColumnInfo(name = "id_foreign_asset")
        val idAsset: Long,

        @ColumnInfo(name = "value_bought")
        val valueBought: Float,

        @ColumnInfo(name = "quantity")
        val quantity: Float,

        @ColumnInfo(name = "date_bought")
        val dateBought: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "active")
        val active: Boolean = true

)
