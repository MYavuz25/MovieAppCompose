package com.example.movieappcompose.domain.repository

import com.example.movieappcompose.data.remote.dto.MovieDetailDto
import com.example.movieappcompose.data.remote.dto.MoviesDto

interface MovieRepository {

    suspend fun getMovie(search:String):MoviesDto
    suspend fun getMovieDetail(imdbId:String):MovieDetailDto
}