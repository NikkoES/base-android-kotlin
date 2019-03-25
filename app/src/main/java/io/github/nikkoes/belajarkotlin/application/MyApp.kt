package io.github.nikkoes.belajarkotlin.application

import android.app.Application
import com.androidnetworking.AndroidNetworking
import io.github.nikkoes.belajarkotlin.utils.TypefaceUtil

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        TypefaceUtil.overrideFont(applicationContext, "SERIF", "fonts/montserat.ttf")


        AndroidNetworking.initialize(this)
        AndroidNetworking.enableLogging()
    }

}