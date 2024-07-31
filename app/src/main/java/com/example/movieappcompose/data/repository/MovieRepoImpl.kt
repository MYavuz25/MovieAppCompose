package com.example.movieappcompose.data.repository

import com.example.movieappcompose.data.remote.dto.MovieDetailDto
import com.example.movieappcompose.data.remote.dto.MoviesDto
import com.example.movieappcompose.data.remote.retrofit.MovieApi
import com.example.movieappcompose.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(private val api: MovieApi):MovieRepository {
    override suspend fun getMovie(search: String): MoviesDto {
        return api.getMovies(search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId)
    }
}