package com.nevrmd.barkbark.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nevrmd.barkbark.domain.BreedEntity

@Database([BreedEntity::class], version = 1)
abstract class BreedDatabase : RoomDatabase() {
    abstract val breedDao: BreedDao
    companion object {
        @Volatile
        private var INSTANCE: BreedDatabase? = null
        fun getInstance(context: Context): BreedDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BreedDatabase::class.java,
                        "dog_breeds_db"
                    ).build()
                }
                return instance
            }
        }
    }
}