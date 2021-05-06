package com.github.ricardopolit.sipmanager.data.asset

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets")
data class Asset(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_asset")
        val id: Long = 0L,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name ="code")
        val code: String,

        @ColumnInfo(name = "current_value")
        val currentValue: Float,

        @ColumnInfo(name = "color")
        val color: String,

        @ColumnInfo(name = "income_type")
        val incomeType: Int,

        @ColumnInfo(name = "active")
        val active: Boolean = true

)
