package com.rilodev.trinitywizards.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rilodev.core.data.repositories.Resource
import com.rilodev.core.domain.model.PersonModel
import com.rilodev.core.domain.usecase.AppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appUseCase: AppUseCase): ViewModel() {
    private val _getPersons = MutableStateFlow<Resource<List<PersonModel>>>(Resource.Loading())
    val getPersons: Flow<Resource<List<PersonModel>>> = _getPersons

    fun getPersons() {
        viewModelScope.launch {
            appUseCase.getPersonUseCase().collect {
                _getPersons.value = it
            }
        }
    }
}