package com.github.ricardopolit.sipmanager.data.assetsoldvi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets_sold_variableincome")
data class AssetSoldVI(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_asset_sold_vi")
        val idAssetSoldVI: Long = 0L,

        @ColumnInfo(name = "id_foreign_asset")
        val idAsset: Long,

        @ColumnInfo(name = "value_sold")
        val valueBought: Float,

        @ColumnInfo(name = "quantity")
        val quantity: Float,

        @ColumnInfo(name = "date_sold")
        val dateBought: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "earnings")
        val earnings: Float,

        @ColumnInfo(name = "active")
        val active: Boolean = true

)
