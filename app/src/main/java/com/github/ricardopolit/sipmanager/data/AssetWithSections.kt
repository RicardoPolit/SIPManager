package com.github.ricardopolit.sipmanager.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class AssetWithSections(
        @Embedded val asset: Asset,
        @Relation(
                parentColumn = "id_asset",
                entityColumn = "id_section",
                associateBy = Junction(SectionAssetCrossRef::class)
        )
        val sections: List<Section>
)
