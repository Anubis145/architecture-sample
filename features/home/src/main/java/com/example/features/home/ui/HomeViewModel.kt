package com.example.features.home.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.common.mvi.BaseViewModel
import com.example.domain.home.use_case.SearchBooksUseCase
import com.example.features.home.ui.mvi.HomeEffect
import com.example.features.home.ui.mvi.HomeEvent
import com.example.features.home.ui.mvi.HomeReducer
import com.example.features.home.ui.mvi.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchBooksUseCase: SearchBooksUseCase,
    savedStateHandle: SavedStateHandle,
    reducer: HomeReducer,
) : BaseViewModel<HomeState, HomeEvent, HomeEffect>(
    initialState = HomeState(),
    reducer = reducer
), HomeIntent {

    private val searchUserInput = MutableStateFlow("")

    init {
        observeSearchValueInput()
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchValueInput() {
        viewModelScope.launch {
            searchUserInput
                .debounce(500L)
                .collectLatest {

                }
        }
    }

    override fun onSearchValueChanged(value: String) {
        viewModelScope.launch {
            sendEvent(HomeEvent.SearchValueChanged(value))
            searchUserInput.emit(value)
        }
    }
}
