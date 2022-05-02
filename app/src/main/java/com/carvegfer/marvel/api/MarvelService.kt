package com.carvegfer.marvel.api

import com.carvegfer.marvel.api.model.CharacterResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("public/characters")
    fun getCharacters(@Query("apiKey")apiKey : String) : Observable<Response<List<CharacterResponse>>>

    @GET("public/characters/{characterId}")
    fun getCharacter(@Path("characterId") id : Number, @Query("apiKey")apiKey : String) : Observable<Response<CharacterResponse>>
}