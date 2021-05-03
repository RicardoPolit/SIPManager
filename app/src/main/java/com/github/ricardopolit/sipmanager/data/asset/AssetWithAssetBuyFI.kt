package com.github.ricardopolit.sipmanager.data.asset

import androidx.room.Embedded
import androidx.room.Relation
import com.github.ricardopolit.sipmanager.data.assetbuyfi.AssetBuyFI

data class AssetWithAssetBuyFI(
        @Embedded val asset: Asset,
        @Relation(
                parentColumn = "id_asset",
                entityColumn = "id_foreign_asset"
        )
        val assetBuyFIs: List<AssetBuyFI>
)
