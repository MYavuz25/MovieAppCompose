package com.example.movieappcompose.presentation.movie_list.view

sealed class MovieListEvent {

    data class Search(val searchString: String):MovieListEvent()
}