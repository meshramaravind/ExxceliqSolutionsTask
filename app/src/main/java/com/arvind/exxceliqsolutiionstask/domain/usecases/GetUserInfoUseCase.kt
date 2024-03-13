package com.arvind.exxceliqsolutiionstask.domain.usecases

import androidx.paging.PagingData
import com.arvind.exxceliqsolutiionstask.data.repository.Repository
import com.arvind.exxceliqsolutiionstask.domain.models.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<UserData>> {
        return repository.getUserInfo()
    }
}