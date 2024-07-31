package com.example.movieappcompose.presentation.movie_list.view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.domain.use_case.get_movies.GetMoviesUseCase
import com.example.movieappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel@Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) :ViewModel() {
    private val _state= mutableStateOf<MovieListState>(MovieListState())
    val state:State<MovieListState> =_state


    private var job:Job?=null

    init {
        getMovies(_state.value.search)
    }
    private fun getMovies(search: String){
        job?.cancel()
        job=getMoviesUseCase.getAllMovies(search).onEach {
            when(it){
                is Resource.Error -> {
                    _state.value= MovieListState(error = it.message?:"ERROR")
                }
                is Resource.Loading -> {
                    _state.value= MovieListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value=MovieListState(movies = it.data?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MovieListEvent){

        when(event){
            is MovieListEvent.Search ->{
                getMovies(event.searchString)
            }
        }
    }


}