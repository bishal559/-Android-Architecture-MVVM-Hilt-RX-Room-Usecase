package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.source.local.dao.PhotoDao
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.source.local.entity.PhotoEntity

/**
 * To manage data items that can be accessed, updated
 * & maintain relationships between them
 *
 * Created by bishal on 12/06/2023
 * */
@Database(entities = [PhotoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val photoDao: PhotoDao

    companion object {
        const val DB_NAME = "ArtGalleryDatabase.db"
    }
}