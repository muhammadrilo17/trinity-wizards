package com.rilodev.trinitywizards.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rilodev.core.data.repositories.Resource
import com.rilodev.core.domain.model.DataModel
import com.rilodev.core.domain.usecase.AppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appUseCase: AppUseCase): ViewModel() {
    private val _dataResult = MutableStateFlow<Resource<List<DataModel>>>(Resource.Loading())
    val dataResult: Flow<Resource<List<DataModel>>> = _dataResult

    fun dataResult() {
        viewModelScope.launch {
            appUseCase.getDataUseCase().collect {
                _dataResult.value = it
            }
        }
    }
}