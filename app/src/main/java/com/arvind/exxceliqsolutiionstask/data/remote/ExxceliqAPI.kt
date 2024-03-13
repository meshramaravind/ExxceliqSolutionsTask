package com.arvind.exxceliqsolutiionstask.data.remote

import com.arvind.exxceliqsolutiionstask.domain.models.UsersInfoBaseResponse
import com.arvind.exxceliqsolutiionstask.utils.Constants.ITEMS_PER_PAGE
import com.arvind.exxceliqsolutiionstask.utils.Constants.STARTING_PAGE
import retrofit2.http.GET
import retrofit2.http.Query

interface ExxceliqAPI {

    @GET("users?")
    suspend fun getUsers(
        @Query("page") page: Int = STARTING_PAGE,
        @Query("per_page") per_page: Int = ITEMS_PER_PAGE,
    ): UsersInfoBaseResponse
}