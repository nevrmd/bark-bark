package com.nevrmd.barkbark.data.source.local

import com.nevrmd.barkbark.data.source.local.database.BreedDao
import com.nevrmd.barkbark.domain.BreedEntity

class BreedRepository(private val dao: BreedDao) {
    val breeds = dao.getAllBreeds()

    suspend fun insert(note: BreedEntity): Long {
        return dao.insertBreed(note)
    }

    fun getBreedById(id: Int): BreedEntity {
        return dao.getBreedById(id)
    }
}