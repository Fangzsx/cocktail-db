package com.fangzsx.retrofit_room.api

import com.fangzsx.retrofit_room.model.RandomCocktailResponse
import retrofit2.Response
import retrofit2.http.GET

interface CocktailApi {

    @GET("random.php")
    suspend fun getRandomCocktail() : Response<RandomCocktailResponse>

}