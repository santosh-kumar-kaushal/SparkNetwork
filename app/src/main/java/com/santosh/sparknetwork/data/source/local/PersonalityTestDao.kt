package com.santosh.sparknetwork.data.source.local

import androidx.room.*
import com.santosh.sparknetwork.domain.model.PersonalityTestData

@Dao
interface PersonalityTestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(personalityTestData: PersonalityTestData)

    @Query("SELECT * FROM PersonalityTestData")
    fun loadAll(): List<PersonalityTestData>

    @Query("DELETE FROM PersonalityTestData")
    fun deleteAll()
}