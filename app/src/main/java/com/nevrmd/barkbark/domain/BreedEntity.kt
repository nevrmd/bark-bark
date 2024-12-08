package com.nevrmd.barkbark.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BreedEntity(
    // General
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String,
    val isHypoallergenic: Boolean,
    // Life Expectancy
    val lifeMin: Int,
    val lifeMax: Int,
    // Not Related
    val pageNumber: Int,
)