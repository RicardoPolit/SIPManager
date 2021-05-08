package com.github.ricardopolit.sipmanager.data.asset

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets")
data class Asset(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_asset")
        var id: Long = 0L,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name ="code")
        var code: String,

        @ColumnInfo(name = "current_value")
        var currentValue: Float,

        @ColumnInfo(name = "color")
        var color: String,

        @ColumnInfo(name = "income_type")
        var incomeType: Int,

        @ColumnInfo(name = "active")
        var active: Boolean = true

)
