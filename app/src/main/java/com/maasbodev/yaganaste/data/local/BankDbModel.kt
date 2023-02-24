package com.maasbodev.yaganaste.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BankDbModel(
    @PrimaryKey val bankName: String,
    @ColumnInfo val description: String,
    @ColumnInfo val age: Int,
    @ColumnInfo val url: String,
)
