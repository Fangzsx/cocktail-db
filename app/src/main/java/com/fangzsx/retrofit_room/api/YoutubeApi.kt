package com.fangzsx.retrofit_room.api

import com.fangzsx.retrofit_room.model.youtube.YoutubeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("v3/search")
    suspend fun searchForTrailer(
        @Query("part") part : String = "snippet",
        @Query("maxResults") noOfResult : Int = 1,
        @Query("order") orderBy : String = "relevance",
        @Query("q") query : String,
        @Query("key") apiKey : String
    ) : Response<YoutubeResponse>
}