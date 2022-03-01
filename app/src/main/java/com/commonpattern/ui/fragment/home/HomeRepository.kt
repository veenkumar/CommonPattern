package com.commonpattern.ui.fragment.home

import com.commonpattern.base.BaseRepository
import com.commonpattern.repository.remote.ApiInterface
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiInterface: ApiInterface)  : BaseRepository() {


    suspend fun search(key : String) = safeApiCall {
        apiInterface.search(key)
    }
    }