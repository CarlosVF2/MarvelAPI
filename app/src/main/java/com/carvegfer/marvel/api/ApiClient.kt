package com.carvegfer.marvel.api

import com.carvegfer.marvel.api.model.CharacterResponse
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {

    private val myAppService: MarvelService
    private val apiKey : String = "f7be2f615e1766a0a4162d98ccf20f20"; //TOTALLY DANGER! TEMPORALLY

    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val clientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        clientBuilder.addInterceptor(loggingInterceptor)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        myAppService = retrofit.create(MarvelService::class.java)
    }

    companion object {
        private val BASE_URL = "https://gateway.marvel.com:443/v1/"
        private var apiClient: ApiClient? = null
        /**
         * Gets my app client.
         *
         * @return the my app client
         */
        val instance: ApiClient get() {
            if (apiClient == null) {
                apiClient = ApiClient()
            }
            return apiClient as ApiClient
        }
    }

    fun getListOfCharacters(limit: Number, offset : Number): Observable<Response<List<CharacterResponse>>> {
        return myAppService.getCharacters(apiKey)
    }

    fun getCharacter(id : Number): Observable<Response<CharacterResponse>> {
        return myAppService.getCharacter(id, apiKey)
    }
}