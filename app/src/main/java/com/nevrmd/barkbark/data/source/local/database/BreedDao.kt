package com.nevrmd.barkbark.data.source.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nevrmd.barkbark.domain.BreedEntity

@Dao
interface BreedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBreed(breed: BreedEntity): Long

    @Update
    suspend fun updateBreed(note: BreedEntity)

    @Delete
    suspend fun deleteBreed(note: BreedEntity)

    @Query("SELECT * FROM BreedEntity")
    fun getAllBreeds(): LiveData<List<BreedEntity>>

    @Query("SELECT * FROM BreedEntity WHERE id = :id")
    fun getBreedById(id: Int): BreedEntity
}