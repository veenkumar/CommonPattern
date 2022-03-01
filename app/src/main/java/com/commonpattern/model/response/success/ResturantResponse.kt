package com.commonpattern.model.response.success

import android.os.Parcelable
import androidx.room.TypeConverters
import com.commonpattern.repository.local.RestaurantEntity
import com.commonpattern.utils.converter.ModelConverter
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResturantResponse (
    @TypeConverters(ModelConverter::class)
    var restaurant: RestaurantEntity
) : Parcelable{
}