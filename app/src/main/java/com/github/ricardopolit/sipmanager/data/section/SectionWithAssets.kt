package com.github.ricardopolit.sipmanager.data.section

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.ricardopolit.sipmanager.data.asset.Asset

data class SectionWithAssets(
        @Embedded val section: Section,
        @Relation(
                parentColumn = "id_section",
                entityColumn = "id_asset",
                associateBy = Junction(SectionAssetCrossRef::class)
        )
        val assets: List<Asset>
)
