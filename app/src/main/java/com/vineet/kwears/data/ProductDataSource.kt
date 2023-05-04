package com.vineet.kwears.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vineet.kwears.data.database.dataentity.WcResponse
import com.vineet.kwears.data.network.repository.ProductRepository

class ProductDataSource(private val repo: ProductRepository):PagingSource<Int, WcResponse>(){
    override fun getRefreshKey(state: PagingState<Int, WcResponse>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WcResponse> {
        return try {
            val page = params.key ?: 1
            val data = repo.getProducts(page, credential)
            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = if (data.isNotEmpty()) page + 1 else null
            )
        }
        catch(e:Exception){
            LoadResult.Error(e)
        }
    }
}