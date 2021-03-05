package com.santosh.sparknetwork.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PersonalityTestData")
data class PersonalityTestData(
    @PrimaryKey
    @ColumnInfo(name = "question")var question: String,
    @ColumnInfo(name = "category")var category: String,
    @ColumnInfo(name = "selectedOption")var selectedOption: String
)