package com.example.features.home.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.common.mvi.BaseViewModel
import com.example.domain.common.ArchResult
import com.example.domain.common.DataError
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
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

private val MIN_QUERY_CHARS = 2

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
                .onEach {
                    if (it.length > MIN_QUERY_CHARS) {
                        sendEvent(HomeEvent.ShowLoading)
                    } else {
                        sendEvent(HomeEvent.SearchResultIsEmpty)
                    }
                }
                .filter { it.length > MIN_QUERY_CHARS }
                .debounce(500L)
                .collectLatest { query ->
                    when (val result = searchBooksUseCase(query)) {
                        is ArchResult.Success -> {
                            sendEvent(HomeEvent.SearchResult(result.data.books))
                        }

                        is ArchResult.Error -> {
                            sendEvent(HomeEvent.HideLoading)
                            when (result.error) {
                                DataError.NetworkError.NoInternetConnection ->
                                    sendEffect(HomeEffect.ShowNoInternetSnackbar)
                                else -> sendEffect(HomeEffect.ShowSomethingWentWrongSnackbar)
                            }
                        }
                    }
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
