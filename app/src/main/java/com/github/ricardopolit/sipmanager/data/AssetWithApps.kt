package com.github.ricardopolit.sipmanager.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class AssetWithApps(
        @Embedded val asset: Asset,
        @Relation(
                parentColumn = "id_asset",
                entityColumn = "id_app",
                associateBy = Junction(AppAssetCrossRef::class)
        )
        val app: List<App>
)
