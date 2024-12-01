package com.nevrmd.barkbark.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BreedEntity(
    // General
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val hypoallergenic: Boolean,
    // Life Expectancy
    val lifeMin: Int,
    val lifeMax: Int,
    // Not Related
    val pageNumber: Int,
)