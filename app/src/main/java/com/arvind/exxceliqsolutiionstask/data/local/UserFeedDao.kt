package com.arvind.exxceliqsolutiionstask.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.arvind.exxceliqsolutiionstask.domain.models.UserData

@Dao
interface UserFeedDao {
    @Upsert
    suspend fun upsertAll(useFeed: List<UserData>)

    @Query("SELECT * FROM UserFeedDB")
    fun pagingSource(): PagingSource<Int, UserData>

    @Query("DELETE FROM UserFeedDB")
    suspend fun clearAll()
}