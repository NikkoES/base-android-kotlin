package io.github.nikkoes.belajarkotlin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Penghuni (
    @SerializedName("id") val id: String?,
    @SerializedName("nama") val nama: String?,
    @SerializedName("no_hp") val hp: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("fasilitas") val fasilitas: String?,
    @SerializedName("alamat") val alamat: String?
) : Serializable