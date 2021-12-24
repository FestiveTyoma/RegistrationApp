package com.myprojects.registrationapp


import com.myprojects.registrationapp.db.User
import com.myprojects.registrationapp.pojo.Albums
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

class NetworkService private constructor() {
    private val mRetrofit: Retrofit

    interface APIService {
        @GET("/search")
        fun getAlbums(@QueryMap map: Map<String, String>): Call<Albums>
        @GET("/lookup")
        fun getAlbumById(@QueryMap map: Map<String, String>): Call<Albums>

    }
    val aPI: APIService
        get() = mRetrofit.create(APIService::class.java)

    companion object {
        private var mInstance: NetworkService? = null
        private const val BASE_URL = "https://itunes.apple.com"

        //Singleton for class
        val instance: NetworkService?
            get() {
                if (mInstance == null) {
                    mInstance = NetworkService()
                }
                return mInstance
            }
    }

    // declare and initialize Retrofit
    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}