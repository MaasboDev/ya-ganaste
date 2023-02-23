package com.maasbodev.yaganaste.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BankDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
	abstract fun bankDao(): BankDao
}