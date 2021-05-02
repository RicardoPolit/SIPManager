package com.github.ricardopolit.sipmanager.data

import androidx.room.*

@Dao
interface AppDAO {

    @Insert
    suspend fun insert(app: App)

    @Update
    suspend fun update(app: App)

    @Query( "SELECT * FROM apps ORDER BY id_app" )
    suspend fun getAllHistoryApps(): List<App>

    @Query( "SELECT * FROM apps WHERE active = 1 ORDER BY id_app" )
    suspend fun getAllApps(): List<App>

    @Query( "SELECT * FROM apps WHERE id_app = :id" )
    suspend fun getApp(id: Long): App?

    @Transaction
    @Query("SELECT * FROM apps WHERE active = 1")
    suspend fun getAppsWithAssets(): List<AppWithAssets>

    @Transaction
    @Query("SELECT * FROM apps")
    suspend fun getAppsHistoryWithAssets(): List<AppWithAssets>

    @Transaction
    @Query("SELECT * from apps WHERE id_app = :id")
    suspend fun getAppWithAssets(id: Long): AppWithAssets?

    @Query("UPDATE apps SET active = 0 WHERE id_app = :id")
    suspend fun deleteApp(id: Long)

    @Query("UPDATE apps SET active = 1 WHERE id_app = :id")
    suspend fun recoverApp(id: Long)

    @Query( "UPDATE apps SET active = 0" )
    suspend fun deleteAllApps()

    @Query("DELETE FROM apps WHERE id_app = :id")
    suspend fun clearApp(id: Long)

    @Query("DELETE FROM apps")
    suspend fun clearAllApps()

}