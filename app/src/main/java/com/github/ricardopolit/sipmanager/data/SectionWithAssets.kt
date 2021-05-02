package com.github.ricardopolit.sipmanager.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SectionWithAssets(
        @Embedded val section: Section,
        @Relation(
                parentColumn = "id_section",
                entityColumn = "id_asset",
                associateBy = Junction(SectionAssetCrossRef::class)
        )
        val assets: List<Asset>
)
