package com.github.ricardopolit.sipmanager.data.assetdividendvi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets_dividend_variableincome")
data class AssetDividendVI(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_asset_dividend_vi")
        val idAssetDividendVI: Long = 0L,

        @ColumnInfo(name = "id_foreign_asset")
        val idAsset: Long,

        @ColumnInfo(name = "figure_returned")
        val figureReturned: Float,

        @ColumnInfo(name = "quantity")
        val quantity: Float,

        @ColumnInfo(name = "date_returned")
        val dateReturned: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "active")
        val active: Boolean = true

)
