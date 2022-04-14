package com.fangzsx.retrofit_room.api

import com.fangzsx.retrofit_room.model.*
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

    @GET("search.php")
    suspend fun getIngredientInfo(
        @Query("i")
        ingredientName : String
    ) : Response<IngredientInfoResponse>

    @GET("search.php")
    suspend fun search(
        @Query("s")
        query: String
    ) : Response<DrinkResponse>


}