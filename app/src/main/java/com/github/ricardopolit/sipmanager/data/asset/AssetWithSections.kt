package com.github.ricardopolit.sipmanager.data.asset

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.ricardopolit.sipmanager.data.section.Section
import com.github.ricardopolit.sipmanager.data.section.SectionAssetCrossRef

data class AssetWithSections(
        @Embedded val asset: Asset,
        @Relation(
                parentColumn = "id_asset",
                entityColumn = "id_section",
                associateBy = Junction(SectionAssetCrossRef::class)
        )
        val sections: List<Section>
)
