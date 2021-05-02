package com.github.ricardopolit.sipmanager.data

import androidx.room.*

@Dao
interface PortfolioDAO {

    @Insert
    suspend fun insert(portfolio: Portfolio)

    @Update
    suspend fun update(portfolio: Portfolio)

    @Query("DELETE FROM portfolios")
    suspend fun clearAllPortfolios()

    @Query("DELETE FROM portfolios WHERE id_portfolio = :id")
    suspend fun clearPortfolio(id: Long)

    @Query("UPDATE portfolios SET active = 0 WHERE id_portfolio = :id")
    suspend fun deletePortfolio(id: Long)

    @Query("UPDATE portfolios SET active = 1 WHERE id_portfolio = :id")
    suspend fun recoverPortfolio(id: Long)

    @Query("SELECT * FROM portfolios ORDER BY date_start")
    suspend fun getAllHistoryPortfolios(): List<Portfolio>

    @Query("SELECT * FROM portfolios WHERE active = 1 ORDER BY date_start")
    suspend fun getAllPortfolios(): List<Portfolio>

    @Query("SELECT * FROM portfolios WHERE id_portfolio = :id")
    suspend fun getPortfolio(id: Long): Portfolio?

    //TODO implement function in Repository getPortfoliosHistoryWithDeposits
    @Transaction
    @Query( "SELECT * FROM portfolios")
    suspend fun getPortfoliosHistoryWithDeposits(): List<PortfolioWithDeposits>

    @Transaction
    @Query( "SELECT * FROM portfolios WHERE active = 1")
    suspend fun getPortfoliosWithDeposits(): List<PortfolioWithDeposits>

    @Transaction
    @Query("SELECT * FROM portfolios WHERE id_portfolio = :id")
    suspend fun getPortfolioWithDeposits(id: Long): PortfolioWithDeposits?

    //TODO implement function in Repository getPortfoliosHistoryWithSections
    @Transaction
    @Query( "SELECT * FROM portfolios")
    suspend fun getPortfoliosHistoryWithSections(): List<PortfolioWithSections>

    @Transaction
    @Query( "SELECT * FROM portfolios WHERE active = 1")
    suspend fun getPortfoliosWithSections(): List<PortfolioWithSections>

    @Transaction
    @Query("SELECT * FROM portfolios WHERE id_portfolio = :id")
    suspend fun getPortfolioWithSections(id: Long): PortfolioWithSections?

}