package com.maasbodev.yaganaste.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BankDao {
    @Query("SELECT * FROM BankDbModel")
    fun getAll(): List<BankDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(banksDbModel: List<BankDbModel>)
}
