package com.commonpattern.repository.remote

import com.commonpattern.model.response.success.ResponseBean
import com.commonpattern.repository.remote.ApiConstant.Companion.SEARCH
import com.commonpattern.repository.remote.ApiConstant.Companion.USER_KEY
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {
    @GET(SEARCH)
    suspend fun search(@Query("q") q: String,
                       @Header("user-key") user_key:String=USER_KEY) : ResponseBean
}