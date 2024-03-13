package com.arvind.exxceliqsolutiionstask.domain.repository

import androidx.paging.PagingData
import com.arvind.exxceliqsolutiionstask.domain.models.UserData
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getUsersInfo(): Flow<PagingData<UserData>>
}