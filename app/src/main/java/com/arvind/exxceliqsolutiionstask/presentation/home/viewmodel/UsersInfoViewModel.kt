package com.arvind.exxceliqsolutiionstask.presentation.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arvind.exxceliqsolutiionstask.domain.models.UserData
import com.arvind.exxceliqsolutiionstask.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersInfoViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private var _getUsersInfo = mutableStateOf<Flow<PagingData<UserData>>>(emptyFlow())
    val usersInfo: State<Flow<PagingData<UserData>>> = _getUsersInfo

    init {
        getUsersInfo()
    }

    private fun getUsersInfo() {
        viewModelScope.launch {
            _getUsersInfo.value = useCases.getUserInfoUseCase().cachedIn(viewModelScope)
        }
    }
}