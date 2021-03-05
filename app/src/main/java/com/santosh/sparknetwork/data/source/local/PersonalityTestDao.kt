package com.santosh.sparknetwork.data.source.local

import androidx.room.*
import com.santosh.sparknetwork.domain.model.PersonalityTestData

@Dao
interface PersonalityTestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(personalityTestData: PersonalityTestData): Long

    @Query("SELECT * FROM PersonalityTestData")
    fun loadAll(): MutableList<PersonalityTestData>

    @Query("DELETE FROM PersonalityTestData")
    fun deleteAll()
}