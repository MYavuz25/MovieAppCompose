package com.example.movieappcompose.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.domain.use_case.get_movie_detail.getMovieDetailUseCase
import com.example.movieappcompose.util.Constants.IMDB_ID
import com.example.movieappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class MovieDetailViewmodel @Inject constructor(
    private val getMovieDetailUseCase: getMovieDetailUseCase,
    private val stateHandle: SavedStateHandle
):ViewModel() {
    private val _state= mutableStateOf<MovieDetailState>(MovieDetailState())
    val state :State<MovieDetailState> = _state

    init {
        stateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }

    private fun getMovieDetail(imdb:String){
        getMovieDetailUseCase.getMovie(imdb).onEach {
            when(it){
                is Resource.Error -> {
                    _state.value= MovieDetailState(error = "ERROR")
                }
                is Resource.Loading -> {
                    _state.value= MovieDetailState(true)
                }
                is Resource.Success -> {
                    _state.value=MovieDetailState(movie = it.data)
                }
            }

        }.launchIn(viewModelScope)
    }

}