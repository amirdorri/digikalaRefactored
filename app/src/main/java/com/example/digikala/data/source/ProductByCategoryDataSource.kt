package com.example.digikala.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.digikala.data.model.home.StoreProduct
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.CategoryRepo

class ProductByCategoryDataSource(
    private val repository: CategoryRepo,
    private val categoryId: String
) : PagingSource<Int, StoreProduct>() {

    override fun getRefreshKey(state: PagingState<Int, StoreProduct>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoreProduct> {
        return try {
            val nextPageNumber = params.key ?: 1
            val isSubCategory = categoryId.any { it.isDigit() }

            val result = if (isSubCategory) {
                repository.getProductBySubCategory(
                    subCategoryId = categoryId,
                    pageSize = params.loadSize.toString(),
                    pageNumber = nextPageNumber.toString()
                )
            } else {
                repository.getProductByCategory(
                    categoryName = categoryId,
                    pageSize = params.loadSize.toString(),
                    pageNumber = nextPageNumber.toString()
                )
            }

            when (result) {
                is NetworkResult.Success -> {
                    val data = result.data ?: emptyList()

                    Log.d(
                        "PagingSource",
                        "Page: $nextPageNumber | Size: ${data.size} | categoryId=$categoryId"
                    )

                    LoadResult.Page(
                        data = data,
                        prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                        nextKey = if (data.isEmpty()) null else nextPageNumber + 1
                    )
                }

                is NetworkResult.Error -> {
                    Log.e("PagingSource", "Error: ${result.message}")
                    LoadResult.Error(Throwable(result.message))
                }

                is NetworkResult.Loading -> {
                    // این حالت نباید برگرده، ولی برای اطمینان خالی می‌دیم
                    LoadResult.Page(
                        data = emptyList(),
                        prevKey = null,
                        nextKey = null
                    )
                }
            }

        } catch (e: Exception) {
            Log.e("PagingSource", "Exception: ${e.localizedMessage}", e)
            LoadResult.Error(e)
        }
    }
}
