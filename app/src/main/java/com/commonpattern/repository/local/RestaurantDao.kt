package com.commonpattern.repository.local

import androidx.room.Dao
import androidx.room.Query
import com.commonpattern.base.BaseDao

@Dao
interface RestaurantDao : BaseDao<RestaurantEntity> {
    @Query("SELECT * FROM Restaurant")
    fun getAllBookMarkRestro(): MutableList<RestaurantEntity>?
}