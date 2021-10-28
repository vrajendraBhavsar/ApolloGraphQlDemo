package com.example.apollographqldemo.viewModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.apollographqldemo.CharactersDataQuery
import com.example.apollographqldemo.model.state.ViewState
import com.example.apollographqldemo.repository.CharRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharViewModel @Inject constructor(private val charRepository: CharRepository): ViewModel() {
    private val _charactersList by lazy { MutableLiveData<ViewState<Response<CharactersDataQuery.Data>>>() }
    val charactersList: LiveData<ViewState<Response<CharactersDataQuery.Data>>> =  _charactersList

    fun queryCharactersList() = viewModelScope.launch {
        _charactersList.postValue(ViewState.Loading())
        try {
            val response = charRepository.getCharList()
            _charactersList.postValue(ViewState.Success(response))
        } catch (e: ApolloException) {
            Log.d("ApolloException", "Failure", e)
            _charactersList.postValue(ViewState.Error("Error fetching characters"))
        }
    }
}