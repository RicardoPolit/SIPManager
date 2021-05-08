package com.github.ricardopolit.sipmanager.data.assetbuyfi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets_buy_fixedincome")
data class AssetBuyFI(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_asset_buy_fi")
        var idAssetBuyFI: Long = 0L,

        @ColumnInfo(name = "id_foreign_asset")
        var idAsset: Long,

        @ColumnInfo(name = "fixedincome")
        var fixedIncome: Float,

        @ColumnInfo(name = "value_bought")
        var valueBought: Float,

        @ColumnInfo(name = "quantity")
        var quantity: Float,

        @ColumnInfo(name = "date_bought")
        var dateBought: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "date_finish")
        var dateFinish: Long,

        @ColumnInfo(name = "active")
        var active: Boolean = true

)
