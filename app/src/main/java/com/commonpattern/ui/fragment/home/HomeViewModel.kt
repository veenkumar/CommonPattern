package com.commonpattern.ui.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commonpattern.model.response.success.ResponseBean
import com.commonpattern.utils.application.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: HomeRepository) : ViewModel() {

    private val _searchResposne: MutableStateFlow<Resource<ResponseBean>> = MutableStateFlow(Resource.Empty)
    val searchResposne : StateFlow<Resource<ResponseBean>>
    get()=_searchResposne

    fun search(search: String){
        viewModelScope.launch {
            _searchResposne.value=Resource.Loading
            _searchResposne.value=repository.search(search)
        }
    }

}