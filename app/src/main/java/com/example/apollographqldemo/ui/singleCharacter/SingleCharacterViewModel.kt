package com.example.apollographqldemo.ui.singleCharacter
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.apollographqldemozz.SingleCharacterDataQuery
import com.example.apollographqldemo.data.model.state.ViewState
import com.example.apollographqldemo.domain.singleCharacter.SingleCharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleCharacterViewModel @Inject constructor(private val singleCharacterRepository: SingleCharacterRepository) : ViewModel() {
    private val _singleCharacter by lazy { MutableLiveData<ViewState<Response<SingleCharacterDataQuery.Data>>>() }
    val singleCharacter: LiveData<ViewState<Response<SingleCharacterDataQuery.Data>>> = _singleCharacter

    fun getSingleCharacterData(id: String) = viewModelScope.launch {
        _singleCharacter.postValue(ViewState.Loading()) //started loading..
        try {
            val response = singleCharacterRepository.getSingleChar(id = id)
            _singleCharacter.postValue(ViewState.Success(response))
        } catch (e: ApolloException) {
            Log.d("ApolloException", "singleCharacter Failure", e)
            _singleCharacter.postValue(ViewState.Error("Error fetching single character"))
        }
    }
}