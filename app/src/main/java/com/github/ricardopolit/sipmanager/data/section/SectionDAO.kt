package com.github.ricardopolit.sipmanager.data.section

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SectionDAO {

    @Insert
    suspend fun insert(section: Section)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSectionAssetCrossRef(join: SectionAssetCrossRef)

    @Update
    suspend fun update(section: Section)

    @Query( "SELECT * FROM sections ORDER BY id_section" )
    fun getAllHistorySections(): LiveData<List<Section>>

    @Query( "SELECT * FROM sections WHERE active = 1 ORDER BY id_section" )
    fun getAllSections(): LiveData<List<Section>>

    @Query( "SELECT * FROM sections WHERE id_section = :id" )
    suspend fun getSection(id: Long): Section?

    @Query("SELECT * FROM sections WHERE id_foreign_portfolio = :idPortfolio AND active = 1 ORDER BY id_section")
    fun getAllSectionsFromPortfolio(idPortfolio: Long): LiveData<List<Section>>

    @Query("SELECT * FROM sections WHERE id_foreign_portfolio = :idPortfolio ORDER BY id_section")
    fun getAllSectionsFromPortfolioHistory(idPortfolio: Long): LiveData<List<Section>>

    @Transaction
    @Query("SELECT * FROM sections")
    fun getSectionsHistoryWithAssets(): LiveData<List<SectionWithAssets>>

    @Transaction
    @Query("SELECT * FROM sections WHERE active = 1")
    fun getSectionsWithAssets(): LiveData<List<SectionWithAssets>>

    @Transaction
    @Query("SELECT * FROM sections WHERE id_section = :id")
    suspend fun getSectionWithAssets(id: Long): SectionWithAssets?

    @Query("UPDATE sections SET active = 0 WHERE id_section = :id")
    suspend fun deleteSection(id: Long)

    @Query("UPDATE sections SET active = 1 WHERE id_section = :id")
    suspend fun recoverSection(id: Long)

    @Query( "UPDATE sections SET active = 0" )
    suspend fun deleteAllSections()

    @Query( "UPDATE sections SET active = 0 WHERE id_foreign_portfolio = :idPortfolio" )
    suspend fun deleteAllSectionsFromPortfolio(idPortfolio: Long)

    @Query( "UPDATE sections SET active = 1 WHERE id_foreign_portfolio = :idPortfolio" )
    suspend fun recoverAllSectionsFromPortfolio(idPortfolio: Long)

    @Query("DELETE FROM sections WHERE id_section = :id")
    suspend fun clearSection(id: Long)

    @Query("DELETE FROM sections")
    suspend fun clearAllSections()

    @Query("DELETE FROM sections WHERE id_foreign_portfolio = :idPortfolio")
    suspend fun clearAllSectionsFromPortfolio(idPortfolio: Long)

}