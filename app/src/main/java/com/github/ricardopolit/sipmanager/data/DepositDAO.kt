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

    @Query( "SELECT * FROM deposits ORDER BY id_deposit" )
    suspend fun getAllHistoryDeposits(): List<Deposit>

    @Query( "SELECT * FROM deposits WHERE active = 1 ORDER BY id_deposit" )
    suspend fun getAllDeposits(): List<Deposit>

    @Query( "SELECT * FROM deposits WHERE id_deposit = :id" )
    suspend fun getDeposit(id: Long): Deposit?

    @Query("SELECT * FROM deposits WHERE id_foreign_portfolio = :idPortfolio AND active = 1 ORDER BY date")
    suspend fun getAllDepositsFromPortfolio(idPortfolio: Long): List<Deposit>

    @Query("SELECT * FROM deposits WHERE id_foreign_portfolio = :idPortfolio ORDER BY date")
    suspend fun getAllDepositsFromPortfolioHistory(idPortfolio: Long): List<Deposit>

    @Query("UPDATE deposits SET active = 0 WHERE id_deposit = :id")
    suspend fun deleteDeposit(id: Long)

    @Query("UPDATE deposits SET active = 1 WHERE id_deposit = :id")
    suspend fun recoverDeposit(id: Long)

    @Query( "UPDATE deposits SET active = 0" )
    suspend fun deleteAllDeposits()

    @Query( "UPDATE deposits SET active = 0 WHERE id_foreign_portfolio = :idPortfolio" )
    suspend fun deleteAllDepositsFromPortfolio(idPortfolio: Long)

    @Query( "UPDATE deposits SET active = 1 WHERE id_foreign_portfolio = :idPortfolio" )
    suspend fun recoverAllDepositsFromPortfolio(idPortfolio: Long)

    @Query("DELETE FROM deposits WHERE id_deposit = :id")
    suspend fun clearDeposit(id: Long)

    @Query("DELETE FROM deposits")
    suspend fun clearAllDeposits()

    @Query("DELETE FROM deposits WHERE id_foreign_portfolio = :idPortfolio")
    suspend fun clearAllDepositsFromPortfolio(idPortfolio: Long)

}