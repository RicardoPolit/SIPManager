package com.github.ricardopolit.sipmanager.data.asset

import androidx.room.Embedded
import androidx.room.Relation
import com.github.ricardopolit.sipmanager.data.assetvaluehistory.AssetValueHistory

data class AssetWithAssetValueHistory(
        @Embedded val asset: Asset,
        @Relation(
                parentColumn = "id_asset",
                entityColumn = "id_foreign_asset"
        )
        val assetValuesHistory: List<AssetValueHistory>
)
