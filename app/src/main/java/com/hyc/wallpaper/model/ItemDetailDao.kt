package com.hyc.wallpaper.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface ItemDetailDao {
    @Query("SELECT * FROM itemDetail")
    fun getAll(): Maybe<List<ItemDetail>>


    @Insert
    fun insert(item: ItemDetail)

    @Query("SELECT * FROM itemDetail WHERE hpcontent_id = (:id)")
    fun select(id: String):Maybe<ItemDetail>

    @Insert
    fun insertAll(items: List<ItemDetail>)


    @Delete
    fun delete(item: ItemDetail)
}