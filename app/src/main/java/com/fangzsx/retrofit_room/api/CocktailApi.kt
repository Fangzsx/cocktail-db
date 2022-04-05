package com.fangzsx.retrofit_room.api

import com.fangzsx.retrofit_room.model.FilterByIngredientResponse
import com.fangzsx.retrofit_room.model.DrinkResponse
import com.fangzsx.retrofit_room.model.IngredientListResponse
import com.fangzsx.retrofit_room.model.RandomCocktailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {

    @GET("random.php")
    suspend fun getRandomCocktail() : Response<RandomCocktailResponse>

    @GET("filter.php")
    suspend fun filterByIngredient(
        @Query("i")
        category : String
    ) : Response<FilterByIngredientResponse>

    @GET("list.php?i=list")
    suspend fun getListOfIngredients() : Response<IngredientListResponse>

    @GET("lookup.php")
    suspend fun getCocktailByID(
        @Query("i")
        id : String?
    ) : Response<DrinkResponse>


}