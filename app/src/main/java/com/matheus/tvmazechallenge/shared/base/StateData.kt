package com.matheus.tvmazechallenge.shared.base

sealed class StateData<out T> {
    data class Success<out T>(val data: T): StateData<T>()
    data class Loading(val nothing: Nothing? = null): StateData<Nothing>()
    data class Failure(val message: String? = null): StateData<Nothing>()
}