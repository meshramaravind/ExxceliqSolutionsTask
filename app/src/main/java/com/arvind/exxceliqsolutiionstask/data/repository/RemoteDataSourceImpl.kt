package com.arvind.exxceliqsolutiionstask.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arvind.exxceliqsolutiionstask.data.paging.UserInfoSource
import com.arvind.exxceliqsolutiionstask.data.remote.ExxceliqAPI
import com.arvind.exxceliqsolutiionstask.domain.models.UserData
import com.arvind.exxceliqsolutiionstask.domain.repository.RemoteDataSource
import com.arvind.exxceliqsolutiionstask.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val exxceliqAPI: ExxceliqAPI
) : RemoteDataSource {
    override fun getUsersInfo(): Flow<PagingData<UserData>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                UserInfoSource(exxceliqAPI = exxceliqAPI)
            }
        ).flow
    }
}