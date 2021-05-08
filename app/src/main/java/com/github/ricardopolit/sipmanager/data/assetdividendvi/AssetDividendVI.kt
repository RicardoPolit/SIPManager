package com.github.ricardopolit.sipmanager.data.assetdividendvi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets_dividend_variableincome")
data class AssetDividendVI(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_asset_dividend_vi")
        var idAssetDividendVI: Long = 0L,

        @ColumnInfo(name = "id_foreign_asset")
        var idAsset: Long,

        @ColumnInfo(name = "figure_returned")
        var figureReturned: Float,

        @ColumnInfo(name = "quantity")
        var quantity: Float,

        @ColumnInfo(name = "date_returned")
        var dateReturned: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "active")
        var active: Boolean = true

)
