package com.github.ricardopolit.sipmanager.data.section

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sections")
data class Section(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_section")
        var id: Long = 0L,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "current_percentage")
        var currPercentage: Float = 0.0F,

        @ColumnInfo(name = "target_percentage")
        var targetPercentage: Float,

        @ColumnInfo(name = "id_foreign_portfolio")
        var idPortfolio: Long,

        @ColumnInfo(name = "earnings")
        var earnings: Float = 0.0F,

        @ColumnInfo(name = "color")
        var color: String,

        @ColumnInfo(name = "active")
        var active: Boolean = true
)
