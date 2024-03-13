//package com.arvind.exxceliqsolutiionstask.data.paging
//
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import androidx.room.withTransaction
//import com.arvind.exxceliqsolutiionstask.data.local.UserFeedDatabase
//import com.arvind.exxceliqsolutiionstask.data.remote.ExxceliqAPI
//import com.arvind.exxceliqsolutiionstask.domain.models.UserData
//import retrofit2.HttpException
//import java.io.IOException
//
//@OptIn(ExperimentalPagingApi::class)
//class UserFeedRemoteMediator(
//    private val userFeedDb: UserFeedDatabase,
//    private val exxceliqAPI: ExxceliqAPI
//) : RemoteMediator<Int, UserData>() {
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, UserData>
//    ): MediatorResult {
//        return try {
//            val loadKey = when (loadType) {
//                LoadType.REFRESH -> 1
//                LoadType.PREPEND -> return MediatorResult.Success(
//                    endOfPaginationReached = true
//                )
//
//                LoadType.APPEND -> {
//                    val lastItem = state.lastItemOrNull()
//                    if (lastItem == null) {
//                        1
//                    } else {
//                        (lastItem.id / state.config.pageSize) + 1
//                    }
//                }
//            }
//
//            val userFeed = exxceliqAPI.getUsers(
//                page = loadKey,
//                per_page = state.config.pageSize
//            )
//
//            userFeedDb.withTransaction {
//                if (loadType == LoadType.REFRESH) {
//                    userFeedDb.dao.clearAll()
//                }
//                val userFeedEntities = userFeed.data.map { it.toUserData() }
//                userFeedDb.dao.upsertAll(userFeedEntities)
//            }
//
//            MediatorResult.Success(
//                endOfPaginationReached = userFeed.data.isEmpty()
//            )
//        } catch (e: IOException) {
//            MediatorResult.Error(e)
//        } catch (e: HttpException) {
//            MediatorResult.Error(e)
//        }
//    }
//
//}