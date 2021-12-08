package com.example.talenttj.data.paging

import androidx.paging.*
import com.example.talenttj.data.RandomDataApi
import com.example.talenttj.data.repositories.MainRepository
import com.example.talenttj.data.room.entities.User
import retrofit2.HttpException
import java.io.IOException


class UserPagingSource(
    private val serverRepository: RandomDataApi,
    private val mainRepository: MainRepository
) : PagingSource<Int, User>() {


    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageIndex = params.key ?: 1
        return try {
            val response = serverRepository.getListServer(size = pageIndex)
            val nextKey =
                if (response.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    pageIndex + (params.loadSize / 5)
                }
            if(response.isNotEmpty()) {
                mainRepository.clearUserTable()
                mainRepository.insert(response)
            }
            LoadResult.Page(
                data = response,
                prevKey = if (pageIndex == 1) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}