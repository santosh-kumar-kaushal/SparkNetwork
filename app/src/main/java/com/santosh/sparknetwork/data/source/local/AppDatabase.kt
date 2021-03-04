package com.santosh.sparknetwork.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.santosh.sparknetwork.domain.model.SparkNetwork

@Database(entities = [SparkNetwork::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "sparkNetwork.db"
    }
}
