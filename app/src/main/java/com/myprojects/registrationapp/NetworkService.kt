package com.myprojects.registrationapp


import com.myprojects.registrationapp.db.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class NetworkService private constructor() {
    private val mRetrofit: Retrofit

    interface APIService {
        @GET("//search?term={searchResult}&entity=album&attribute=albumTerm&lang={language}")
        fun getAlbums(@Path("searchResult") searchResult: String,
                      @Path("language") language: String): Call<User?>?
    }
    val aPI: APIService
        get() = mRetrofit.create<APIService>(APIService::class.java)

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