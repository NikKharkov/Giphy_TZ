package com.example.giphy_tz.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.giphy_tz.data.GiphyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GifListViewModel @Inject constructor(
    giphyRepository: GiphyRepository
) : ViewModel() {

    val gifs = giphyRepository.getGifs().cachedIn(viewModelScope)
}