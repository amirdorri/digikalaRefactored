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

            when (result) {
                is NetworkResult.Success -> {
                    val data = result.data ?: emptyList()
                    Log.d("PagingDebug", "✅ UserCommentsDataSource → Loaded ${data.size} items")

                    LoadResult.Page(
                        data = data,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (data.isEmpty()) null else page + 1 // 🎯 اینجا مشکل بود!
                    )
                }

                is NetworkResult.Error -> {
                    val message = result.message ?: "Unknown error"
                    Log.e("PagingDebug", "❌ UserCommentsDataSource → Error: $message")

                    // 🚨 اگه پیام "کامنت یافت نشد!" باشه، یعنی end of pagination
                    if (message.contains("کامنت یافت نشد") || message.contains("یافت نشد")) {
                        Log.d("PagingDebug", "🏁 End of pagination reached")
                        LoadResult.Page(
                            data = emptyList(),
                            prevKey = if (page == 1) null else page - 1,
                            nextKey = null // End of pagination
                        )
                    } else {
                        LoadResult.Error(Throwable(message))
                    }
                }

                is NetworkResult.Loading -> {
                    LoadResult.Page(
                        data = emptyList(),
                        prevKey = null,
                        nextKey = null
                    )
                }
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


