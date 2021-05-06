package com.github.ricardopolit.sipmanager.data.portfolio

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PortfolioDAO {

    @Insert
    suspend fun insert(portfolio: Portfolio)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPortfolios( portfolios: List<Portfolio> )

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
    fun getAllHistoryPortfolios(): LiveData<List<Portfolio>>

    @Query("SELECT * FROM portfolios WHERE active = 1 ORDER BY date_start")
    fun getAllPortfolios(): LiveData<List<Portfolio>>

    @Query("SELECT * FROM portfolios WHERE id_portfolio = :id")
    suspend fun getPortfolio(id: Long): Portfolio?

    @Transaction
    @Query( "SELECT * FROM portfolios")
    fun getPortfoliosHistoryWithDeposits(): LiveData<List<PortfolioWithDeposits>>

    @Transaction
    @Query( "SELECT * FROM portfolios WHERE active = 1")
    fun getPortfoliosWithDeposits(): LiveData<List<PortfolioWithDeposits>>

    @Transaction
    @Query("SELECT * FROM portfolios WHERE id_portfolio = :id")
    suspend fun getPortfolioWithDeposits(id: Long): PortfolioWithDeposits?

    @Transaction
    @Query( "SELECT * FROM portfolios")
    fun getPortfoliosHistoryWithSections(): LiveData<List<PortfolioWithSections>>

    @Transaction
    @Query( "SELECT * FROM portfolios WHERE active = 1")
    fun getPortfoliosWithSections(): LiveData<List<PortfolioWithSections>>

    @Transaction
    @Query("SELECT * FROM portfolios WHERE id_portfolio = :id")
    suspend fun getPortfolioWithSections(id: Long): PortfolioWithSections?

}