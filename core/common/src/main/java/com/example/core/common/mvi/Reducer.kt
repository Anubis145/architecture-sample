package com.example.core.common.mvi

interface Reducer<State: Reducer.State, Event: Reducer.Event, Effect: Reducer.Effect> {
    interface State
    interface Event
    interface Effect

    fun reduce(previousState: State, event: Event) : Pair<State, Effect?>
}