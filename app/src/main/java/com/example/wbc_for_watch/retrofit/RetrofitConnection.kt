package com.example.wbc_for_watch.retrofit

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnection {

    companion object {
        private const val BASE_URL = "http://apis.data.go.kr"
        private var INSTANCE: Retrofit? = null

        fun getInstance(): Retrofit {
            if (INSTANCE == null) {
                INSTANCE = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(
                        TikXmlConverterFactory.create(
                            TikXml.Builder().exceptionOnUnreadXml(false).build()
                        )
                    ).build()
            }
            return INSTANCE!!
        }
    }
}