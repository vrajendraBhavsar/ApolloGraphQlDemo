package com.example.apollographqldemo.ui.allCharacters
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.apollographqldemo.CharactersDataQuery
import com.example.apollographqldemo.data.model.state.ViewState
import com.example.apollographqldemo.domain.allCharacters.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharViewModel @Inject constructor(private val charactersRepository: CharactersRepository): ViewModel() {
    private val _charactersList by lazy { MutableLiveData<ViewState<Response<CharactersDataQuery.Data>>>() }
    val charactersList: LiveData<ViewState<Response<CharactersDataQuery.Data>>> =  _charactersList

    fun getCharactersData() = viewModelScope.launch {
        _charactersList.postValue(ViewState.Loading())
        try {
            val response = charactersRepository.getCharList()
            _charactersList.postValue(ViewState.Success(response))
        } catch (e: ApolloException) {
            Log.d("ApolloException", "Failure", e)
            _charactersList.postValue(ViewState.Error("Error fetching characters"))
        }
    }
}