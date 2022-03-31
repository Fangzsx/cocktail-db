package com.fangzsx.retrofit_room.retrofit

import com.fangzsx.retrofit_room.Constants.Constants.Companion.BASE_URL
import com.fangzsx.retrofit_room.api.CocktailApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val cocktailApi by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CocktailApi::class.java)

    }

}