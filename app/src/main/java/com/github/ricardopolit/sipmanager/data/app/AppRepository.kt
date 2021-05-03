package com.github.ricardopolit.sipmanager.data.app

import androidx.lifecycle.LiveData

class AppRepository private constructor(
        private val appDAO: AppDAO){

    companion object{
        @Volatile private var instance: AppRepository? = null

        fun getInstance(appDAO: AppDAO) =
                instance ?: synchronized(this){
                    instance ?: AppRepository(appDAO).also { instance = it }
                }
    }

    suspend fun insertApp(app: App){
        appDAO.insert(app)
    }

    suspend fun insertAppAssetCrossRef(idApp: Long, idAsset: Long){
        val join = AppAssetCrossRef(idApp,idAsset)
        appDAO.insertAppAssetCrossRef(join)
    }

    suspend fun updateApp(app: App){
        appDAO.update(app)
    }

    suspend fun clearAllApps(){
        appDAO.clearAllApps()
    }

    suspend fun clearApp(id: Long){
        appDAO.clearApp(id)
    }

    suspend fun deleteApp(id: Long){
        appDAO.deleteApp(id)
    }

    suspend fun recoverApp(id: Long){
        appDAO.recoverApp(id)
    }

    suspend fun deleteAllApps(){
        appDAO.deleteAllApps()
    }

    fun getAllAppsHistory(): LiveData<List<App>> {
        return appDAO.getAllHistoryApps()
    }

    fun getAllApps(): LiveData<List<App>> {
        return appDAO.getAllApps()
    }

    suspend fun getApp(id: Long): App? {
        return appDAO.getApp(id)
    }

    fun getAppsHistoryWithAssets(): LiveData<List<AppWithAssets>> {
        return appDAO.getAppsHistoryWithAssets()
    }

    fun getAppsWithAssets(): LiveData<List<AppWithAssets>> {
        return appDAO.getAppsWithAssets()
    }

    suspend fun getAppWithAssets(id: Long): AppWithAssets? {
        return appDAO.getAppWithAssets(id)
    }

}