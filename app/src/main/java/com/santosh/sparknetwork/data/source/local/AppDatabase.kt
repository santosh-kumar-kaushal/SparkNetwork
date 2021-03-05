package com.santosh.sparknetwork.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.santosh.sparknetwork.data.source.local.PersonalityTestDao
import com.santosh.sparknetwork.domain.model.PersonalityTestData

@Database(entities = [PersonalityTestData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val personalityTestDao: PersonalityTestDao

}
