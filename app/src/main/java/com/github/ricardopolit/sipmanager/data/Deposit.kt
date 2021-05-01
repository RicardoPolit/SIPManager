package com.github.ricardopolit.sipmanager.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deposits")
data class Deposit(

        @PrimaryKey(autoGenerate = true)
        val id: Long = 0L,

        @ColumnInfo(name = "date")
        val date: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "amount")
        val amount: Long,

        @ColumnInfo(name = "id_portfolio")
        val idPortfolio: Long,

        @ColumnInfo(name = "active")
        val active: Boolean = true
)
