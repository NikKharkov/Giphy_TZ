package com.example.giphy_tz.data

import com.google.gson.annotations.SerializedName

data class GiphyResponse(@SerializedName("data") val data: List<GifObject>)

data class GifObject(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("images") val images: GifImages
)

data class GifImages(
    @SerializedName("original") val original: GifUrl,
    @SerializedName("fixed_height") val preview: GifUrl
)

data class GifUrl(@SerializedName("url") val url: String)