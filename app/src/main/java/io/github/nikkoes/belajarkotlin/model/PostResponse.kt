package io.github.nikkoes.belajarkotlin.model

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("data") val data: String?
)