package com.github.ricardopolit.sipmanager.data.deposit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deposits")
data class Deposit(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_deposit")
        var id: Long = 0L,

        @ColumnInfo(name = "date")
        var date: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "amount")
        var amount: Float,

        @ColumnInfo(name = "id_foreign_portfolio")
        var idPortfolio: Long,

        @ColumnInfo(name = "active")
        var active: Boolean = true
)
