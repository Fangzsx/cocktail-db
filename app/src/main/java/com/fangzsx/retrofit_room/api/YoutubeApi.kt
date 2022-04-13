package com.fangzsx.retrofit_room.api

import com.fangzsx.retrofit_room.model.youtube.YoutubeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("search")
    suspend fun searchForVideo(
        @Query("part") part : String,
        @Query("maxResults") noOfResult : Int,
        @Query("order") orderBy : String,
        @Query("q") query : String,
        @Query("key") apiKey : String
    ) : Response<YoutubeResponse>
}