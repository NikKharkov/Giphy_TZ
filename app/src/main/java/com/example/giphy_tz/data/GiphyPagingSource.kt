package com.example.giphy_tz.data

import androidx.paging.PagingSource
import androidx.paging.PagingState

class GiphyPagingSource(
    private val giphyApiService: GiphyApiService,
    private val apiKey: String
) : PagingSource<Int, GifObject>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifObject> {
        val offset = params.key ?: 0

        return try {
            val response = giphyApiService.getTrending(apiKey, params.loadSize, offset)

            LoadResult.Page(
                data = response.data,
                prevKey = if (offset == 0) null else offset - params.loadSize,
                nextKey = if (response.data.isEmpty()) null else offset + params.loadSize
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GifObject>): Int? {
        return state.anchorPosition
    }
}