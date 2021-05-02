package com.github.ricardopolit.sipmanager.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apps")
data class App(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_app")
        var id: Long = 0L,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "active")
        var active: Boolean = true
)
