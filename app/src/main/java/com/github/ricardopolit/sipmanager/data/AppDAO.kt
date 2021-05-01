package com.github.ricardopolit.sipmanager.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppDAO {

    @Insert
    suspend fun insert(app: App)

    @Update
    suspend fun update(app: App)

    @Query( "SELECT * FROM apps ORDER BY id" )
    suspend fun getAllHistoryApps(): List<App>

    @Query( "SELECT * FROM apps WHERE active = 1 ORDER BY id" )
    suspend fun getAllApps(): List<App>

    @Query( "SELECT * FROM apps WHERE id = :id" )
    suspend fun getApp(id: Long): App?

    @Query("UPDATE apps SET active = 0 WHERE id = :id")
    suspend fun deleteApp(id: Long)

    @Query( "UPDATE apps SET active = 0" )
    suspend fun deleteAllApps()

    @Query("DELETE FROM apps WHERE id = :id")
    suspend fun clearApp(id: Long)

    @Query("DELETE FROM apps")
    suspend fun clearAllApps()

}