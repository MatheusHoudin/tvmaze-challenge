package com.matheus.tvmazechallenge.shared.error

import com.matheus.tvmazechallenge.shared.base.StateData

object Failure {
    val noInternetConnectionFailure =
        StateData.Failure(message = "No internet connection available, please try again")
    val unexpectedFailure =
        StateData.Failure(message = "Sorry, an unexpected error has occurred, please try again")
}