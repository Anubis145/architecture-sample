package com.example.core.common.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State: Reducer.State, Event: Reducer.Event, Effect: Reducer.Effect>(
    initialState: State,
    private val reducer: Reducer<State, Event, Effect>
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _effect = Channel<Effect>(capacity = Channel.CONFLATED)
    val effect = _effect.receiveAsFlow()

    fun sendEvent(event: Event) {
        val (state, effect) = reducer.reduce(_state.value, event)

        _state.update { state }
        effect?.let(::sendEffect)
    }

    fun sendEffect(effect: Effect) {
        _effect.trySend(effect)
    }
}
