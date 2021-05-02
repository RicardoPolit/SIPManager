package com.github.ricardopolit.sipmanager.data

import androidx.room.Embedded
import androidx.room.Relation

data class PortfolioWithSections(
        @Embedded val portfolio: Portfolio,
        @Relation(
                parentColumn = "id_portfolio",
                entityColumn = "id_foreign_portfolio"
        )
        val sections: List<Section>
)
