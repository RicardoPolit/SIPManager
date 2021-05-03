package com.github.ricardopolit.sipmanager.data.portfolio

import androidx.room.Embedded
import androidx.room.Relation
import com.github.ricardopolit.sipmanager.data.deposit.Deposit

data class PortfolioWithDeposits(
        @Embedded val portfolio: Portfolio,
        @Relation(
                parentColumn = "id_portfolio",
                entityColumn = "id_foreign_portfolio"
        )
        val deposits: List<Deposit>
)
