package com.github.ricardopolit.sipmanager.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PortfolioDAO {

    @Insert
    suspend fun insert(portfolio: Portfolio)

    @Update
    suspend fun update(portfolio: Portfolio)

    @Query("DELETE FROM portfolio")
    suspend fun clearAll()

    @Query("DELETE FROM portfolio WHERE id = :id")
    suspend fun clearPortfolio(id: Long)

    @Query("UPDATE portfolio SET active = 0 WHERE id = :id")
    suspend fun deletePortfolio(id: Long)

    @Query("SELECT * FROM portfolio ORDER BY date_start")
    suspend fun getAllPortfoliosHistory(): List<Portfolio>

    @Query("SELECT * FROM portfolio WHERE active = 1 ORDER BY date_start")
    suspend fun getAllPortfolios(): List<Portfolio>

    @Query("SELECT * FROM portfolio WHERE id = :id")
    suspend fun getPortfolio(id: Long): Portfolio?

}