package com.arvind.exxceliqsolutiionstask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arvind.exxceliqsolutiionstask.domain.models.UserData

@Database(entities = [UserData::class], version = 1, exportSchema = true)
abstract class UserFeedDatabase : RoomDatabase() {

    abstract val dao: UserFeedDao
}