package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.source.local.entity.PhotoEntity

/**
 * it provides access to [Photo] underlying database
 *
 * Created by bishal on 12/06/2023
 * */
@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: PhotoEntity): Long

    @Query("SELECT * FROM Photo")
    fun loadAll(): MutableList<PhotoEntity>

    @Delete
    fun delete(photo: PhotoEntity)

    @Query("DELETE FROM Photo")
    fun deleteAll()

    @Query("SELECT * FROM Photo where id = :photoId")
    fun loadOneByPhotoId(photoId: Long): PhotoEntity?

    @Query("SELECT * FROM Photo where title = :photoTitle")
    fun loadOneByPhotoTitle(photoTitle: String): PhotoEntity?

    @Update
    fun update(photo: PhotoEntity)
}
