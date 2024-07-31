package com.example.movieappcompose.presentation.movie_list.view

import com.example.movieappcompose.domain.model.Movie

data class MovieListState(
    val isLoading:Boolean=false,
    val movies:List<Movie> = emptyList(),
    val error: String = "",
    val search:String = "Star Wars"
)
