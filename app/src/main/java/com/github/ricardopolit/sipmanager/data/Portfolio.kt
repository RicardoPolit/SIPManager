package com.github.ricardopolit.sipmanager.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "portfolio" )
data class Portfolio(

        @PrimaryKey(autoGenerate = true)
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
        var totalDeposits: Int = 0,

        @ColumnInfo(name = "earnings")
        var earnings: Int = 0,

        @ColumnInfo(name = "color")
        var color: String,

        @ColumnInfo(name = "active")
        var active: Boolean = true
)