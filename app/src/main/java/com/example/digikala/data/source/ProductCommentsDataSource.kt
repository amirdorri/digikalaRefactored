package com.example.digikala.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.digikala.data.model.product_detail.ProductComment
import com.example.digikala.repository.ProductDetailsRepo


class ProductCommentsDataSource(
    private val repository: ProductDetailsRepo,
    val productId: String
) : PagingSource<Int, ProductComment>() {

    override fun getRefreshKey(state: PagingState<Int, ProductComment>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductComment> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repository.getAllProductComments(
                pageNumber = nextPageNumber.toString(),
                pageSize = "5",
                id = productId
            ).data

            LoadResult.Page(
                data = response!!,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )

        } catch (e: Exception) {
            Log.d("3636", "error:$e ")
            LoadResult.Error(e)
        }
    }
}

