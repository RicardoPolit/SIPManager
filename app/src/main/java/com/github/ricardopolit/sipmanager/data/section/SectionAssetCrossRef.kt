package com.github.ricardopolit.sipmanager.data.section

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
        tableName = "sections_assets__crossref",
        primaryKeys = ["id_section","id_asset"])
data class SectionAssetCrossRef(

        @ColumnInfo(name = "id_section")
        val sectionId: Long,

        @ColumnInfo(name = "id_asset")
        val assetId: Long
)
