package com.arvind.exxceliqsolutiionstask.data.repository

import androidx.paging.PagingData
import com.arvind.exxceliqsolutiionstask.domain.models.UserData
import com.arvind.exxceliqsolutiionstask.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource
) {
    fun getUserInfo(): Flow<PagingData<UserData>> {
        return remote.getUsersInfo()
    }
}