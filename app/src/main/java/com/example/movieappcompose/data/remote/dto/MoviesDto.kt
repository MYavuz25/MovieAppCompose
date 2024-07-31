package com.example.movieappcompose.data.remote.dto

import com.example.movieappcompose.domain.model.Movie

data class MoviesDto(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)
fun MoviesDto.toMovie():List<Movie>{
    return Search.map { search ->
        Movie(search.Poster,search.Title,search.Year,search.imdbID)
    }
}