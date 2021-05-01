package com.github.ricardopolit.sipmanager.data

import androidx.room.Embedded
import androidx.room.Relation

data class PortfolioWithDeposits(
        @Embedded val portfolio: Portfolio,
        @Relation(
                parentColumn = "id",
                entityColumn = "id_portfolio"
        )
        val deposits: List<Deposit>
)
