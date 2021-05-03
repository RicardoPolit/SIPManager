package com.github.ricardopolit.sipmanager.data.assetbuyfi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets_buy_fixedincome")
data class AssetBuyFI(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_asset_buy_fi")
        val idAssetBuyFI: Long = 0L,

        @ColumnInfo(name = "id_foreign_asset")
        val idAsset: Long,

        @ColumnInfo(name = "fixedincome")
        val fixedIncome: Float,

        @ColumnInfo(name = "value_bought")
        val valueBought: Float,

        @ColumnInfo(name = "quantity")
        val quantity: Float,

        @ColumnInfo(name = "date_bought")
        val dateBought: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "date_finish")
        val dateFinish: Long,

        @ColumnInfo(name = "active")
        val active: Boolean = true

)
