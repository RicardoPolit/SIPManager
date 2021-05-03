package com.github.ricardopolit.sipmanager.data.section

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sections")
data class Section(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_section")
        val id: Long = 0L,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "current_percentage")
        val currPercentage: Float = 0.0F,

        @ColumnInfo(name = "target_percentage")
        val targetPercentage: Float,

        @ColumnInfo(name = "id_foreign_portfolio")
        val idPortfolio: Long,

        @ColumnInfo(name = "earnings")
        val earnings: Float = 0.0F,

        @ColumnInfo(name = "color")
        var color: String,

        @ColumnInfo(name = "active")
        val active: Boolean = true
)
