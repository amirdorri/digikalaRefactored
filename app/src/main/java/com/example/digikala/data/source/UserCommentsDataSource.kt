package com.example.digikala.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.digikala.data.model.product_detail.ProductComment
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.CommentsRepo
import com.example.digikala.repository.ProductDetailsRepo

class UserCommentsDataSource(
    private val repository: CommentsRepo,
    val token: String
) : PagingSource<Int, ProductComment>() {

    override fun getRefreshKey(state: PagingState<Int, ProductComment>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductComment> {
        val page = params.key ?: 1
        Log.d("PagingDebug", "🔹 UserCommentsDataSource → Start load, page=$page, token=$token")

        return try {
            val result = repository.getUserComments(
                pageNumber = page.toString(),
                pageSize = "5",
                token = token
            )
            Log.d("PagingDebug", "🔹 UserCommentsDataSource → NetworkResult: $result")

            if (result is NetworkResult.Success && result.data != null) {
                val data = result.data
                Log.d("PagingDebug", "✅ UserCommentsDataSource → Loaded ${data.size} items")

                LoadResult.Page(
                    data = data,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (data.isEmpty()) null else page + 1
                )
            } else {
                Log.e("PagingDebug", "❌ UserCommentsDataSource → Error: ${result.message}")
                LoadResult.Error(Throwable(result.message ?: "Unknown error"))
            }

        } catch (e: Exception) {
            Log.e("PagingDebug", "💥 UserCommentsDataSource → Exception: ${e.localizedMessage}", e)
            LoadResult.Error(e)
        }
    }
}





//class UserCommentsDataSource(
//    private val repository: CommentsRepo,
//    val token: String
//) : PagingSource<Int, ProductComment>() {
//
//    override fun getRefreshKey(state: PagingState<Int, ProductComment>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductComment> {
//        return try {
//            val nextPageNumber = params.key ?: 1
//            val response = repository.getUserComments(
//                pageNumber = nextPageNumber.toString(),
//                pageSize = "5",
//                token = token
//            ).data
//
//            LoadResult.Page(
//                data = response!!,
//                prevKey = null,
//                nextKey = nextPageNumber + 1
//            )
//
//        } catch (e: Exception) {
//            Log.d("3636", "error:$e ")
//            LoadResult.Error(e)
//        }
//    }
//}


