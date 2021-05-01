package com.github.ricardopolit.sipmanager.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DepositDAO {

    @Insert
    suspend fun insert(deposit: Deposit)

    @Update
    suspend fun update(deposit: Deposit)

    @Query( "SELECT * FROM deposits ORDER BY id" )
    suspend fun getAllHistoryDeposits(): List<Deposit>

    @Query( "SELECT * FROM deposits WHERE active = 1 ORDER BY id" )
    suspend fun getAllDeposits(): List<Deposit>

    @Query( "SELECT * FROM deposits WHERE id = :id" )
    suspend fun getDeposit(id: Long): Deposit?

    @Query("SELECT * FROM deposits WHERE id_portfolio = :idPortfolio AND active = 1 ORDER BY date")
    suspend fun getAllDepositsFromPortfolio(idPortfolio: Long): List<Deposit>

    @Query("SELECT * FROM deposits WHERE id_portfolio = :idPortfolio ORDER BY date")
    suspend fun getAllDepositsFromPortfolioHistory(idPortfolio: Long): List<Deposit>

    @Query("UPDATE deposits SET active = 0 WHERE id = :id")
    suspend fun deleteDeposit(id: Long)

    @Query( "UPDATE deposits SET active = 0" )
    suspend fun deleteAllDeposits()

    @Query("DELETE FROM deposits WHERE id = :id")
    suspend fun clearDeposit(id: Long)

    @Query("DELETE FROM deposits")
    suspend fun clearAllDeposits()

}