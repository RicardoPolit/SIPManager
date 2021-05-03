package com.github.ricardopolit.sipmanager.data.asset

import androidx.room.Embedded
import androidx.room.Relation
import com.github.ricardopolit.sipmanager.data.assetbuyvi.AssetBuyVI

data class AssetWithAssetBuyVI(
        @Embedded val asset: Asset,
        @Relation(
                parentColumn = "id_asset",
                entityColumn = "id_foreign_asset"
        )
        val assetBuyVIs: List<AssetBuyVI>
)
