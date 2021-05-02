package com.github.ricardopolit.sipmanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.ricardopolit.sipmanager.util.DatabasekeyMgr
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(
        entities = [
            Portfolio::class,
            Deposit::class,
            Section::class,
            Asset::class,
            App::class,
            SectionAssetCrossRef::class,
            AppAssetCrossRef::class],
        version = 1,
        exportSchema = false)
abstract class SIPManagerDatabase(): RoomDatabase(){

    abstract val portfolioDAO: PortfolioDAO
    abstract val depositDAO: DepositDAO
    abstract val sectionDAO: SectionDAO
    abstract val assetDAO: AssetDAO
    abstract val appDAO: AppDAO

    companion object{

        @Volatile
        private var INSTANCE: SIPManagerDatabase? = null

        fun getInstance(context: Context, password: String): SIPManagerDatabase{

            synchronized(this){
                var instance = INSTANCE

                if(instance == null){

                    val dbKey = DatabasekeyMgr.getCharKey(password,context)
                    val supportFactory = SupportFactory(SQLiteDatabase.getBytes(dbKey))
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            SIPManagerDatabase::class.java,
                            "sipmanager_database")
//                            .openHelperFactory(supportFactory)
                            .fallbackToDestructiveMigration()
                            .build()

                    INSTANCE = instance
                }

                return instance
            }

        }

    }

}