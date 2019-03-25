package io.github.nikkoes.belajarkotlin.data

class Constant {

    companion object {
        private val WEB_URL = "https://kosankoding.co.id/kosanku/"
        private val WEB_URL_API = WEB_URL + "public/"

        val API_PENGHUNI = WEB_URL_API + "penghuni/"
        val GET_PENGHUNI = API_PENGHUNI
        val CREATE_PENGHUNI = API_PENGHUNI
        val UPDATE_PENGHUNI = API_PENGHUNI + "edit/"
        val DELETE_PENGHUNI = API_PENGHUNI + "delete/"
    }
}