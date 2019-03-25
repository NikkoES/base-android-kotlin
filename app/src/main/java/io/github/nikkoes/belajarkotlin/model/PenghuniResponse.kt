package io.github.nikkoes.belajarkotlin.model

import com.google.gson.annotations.SerializedName

data class PenghuniResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("data") val data: ArrayList<Penghuni>
)