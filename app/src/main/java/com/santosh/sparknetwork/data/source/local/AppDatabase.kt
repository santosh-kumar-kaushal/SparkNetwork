package com.santosh.sparknetwork.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.santosh.sparknetwork.data.source.local.AppDatabase.Companion.DB_VERSION
import com.santosh.sparknetwork.domain.model.PersonalityTestData

@Database(entities = [PersonalityTestData::class], version = DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val personalityTestDao: PersonalityTestDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "PersonalityTestData.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: build(
                        context
                    ).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,
                DB_NAME
            ).build()

    }

}
