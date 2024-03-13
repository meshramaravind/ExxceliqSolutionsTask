package com.arvind.exxceliqsolutiionstask.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arvind.exxceliqsolutiionstask.data.remote.ExxceliqAPI
import com.arvind.exxceliqsolutiionstask.domain.models.UserData
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserInfoSource @Inject constructor(
    private val exxceliqAPI: ExxceliqAPI,
) : PagingSource<Int, UserData>() {
    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {
        return try {
            val nextPage = params.key ?: 1
            val usersData =
                exxceliqAPI.getUsers(page = nextPage)

            LoadResult.Page(
                data = usersData.data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (usersData.data.isEmpty()) null else usersData.page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}