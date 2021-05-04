package com.github.ricardopolit.sipmanager.data.portfolio

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "portfolios" )
data class Portfolio(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_portfolio")
        var id: Long = 0L,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "goal")
        var goal: String,

        @ColumnInfo(name = "date_start")
        var dateStart: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "date_finish")
        var dateFinish: Long,

        @ColumnInfo(name = "total_deposits")
        var totalDeposits: Float = 0.0F,

        @ColumnInfo(name = "earnings")
        val earnings: Float = 0.0F,

        @ColumnInfo(name = "color")
        var color: String,

        @ColumnInfo(name = "currency")
        var currency: String,

        @ColumnInfo(name = "active")
        var active: Boolean = true
)