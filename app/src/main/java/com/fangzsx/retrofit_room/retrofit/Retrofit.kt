package com.fangzsx.retrofit_room.retrofit

import com.fangzsx.retrofit_room.Constants.Constants.Companion.COCKTAIL_DB_BASE_URL
import com.fangzsx.retrofit_room.Constants.Constants.Companion.YOUTUBE_BASE_URL
import com.fangzsx.retrofit_room.api.CocktailApi
import com.fangzsx.retrofit_room.api.YoutubeApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val cocktailApi by lazy{
        Retrofit.Builder()
            .baseUrl(COCKTAIL_DB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CocktailApi::class.java)

    }

    val youtubeApi by lazy{
        Retrofit.Builder()
            .baseUrl(YOUTUBE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YoutubeApi::class.java)

    }

}