package com.example.giphy_tz.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.giphy_tz.util.GIPHY_API_KEY
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GiphyRepository @Inject constructor(
    private val giphyApiService: GiphyApiService
) {
    fun getGifs() = Pager(
        config = PagingConfig(pageSize = 25, enablePlaceholders = false),
        pagingSourceFactory = {
            GiphyPagingSource(
                giphyApiService = giphyApiService,
                apiKey = GIPHY_API_KEY
            )
        }
    ).flow
}