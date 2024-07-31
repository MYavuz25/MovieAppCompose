package com.example.movieappcompose.data.remote.retrofit

import com.example.movieappcompose.data.remote.dto.MovieDetailDto
import com.example.movieappcompose.data.remote.dto.MoviesDto
import com.example.movieappcompose.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    //https://www.omdbapi.com/?s=batman&apikey=228f118f
    @GET(".")
    suspend fun getMovies(
        @Query("s")  searchString:String,
        @Query("apikey") apiKey:String=API_KEY
    ):MoviesDto

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i")  imdbId:String,
        @Query("apikey") apiKey:String=API_KEY
    ):MovieDetailDto
}