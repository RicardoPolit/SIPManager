package com.github.ricardopolit.sipmanager.data.app

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.ricardopolit.sipmanager.data.asset.Asset

data class AppWithAssets(
        @Embedded val app: App,
        @Relation(
                parentColumn = "id_app",
                entityColumn = "id_asset",
                associateBy = Junction(AppAssetCrossRef::class)
        )
        val assets: List<Asset>
)
