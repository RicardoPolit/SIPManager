package com.github.ricardopolit.sipmanager.data

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
        tableName = "apps_assets__crossref",
        primaryKeys = ["id_app","id_asset"])
data class AppAssetCrossRef(
        @ColumnInfo(name = "id_app")
        val appId: Long,

        @ColumnInfo(name = "id_asset")
        val assetId: Long
)


