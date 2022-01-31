package com.matheus.tvmazechallenge.shared.error

import com.matheus.tvmazechallenge.shared.base.StateData

object Failure {
    val noInternetConnectionFailure =
        StateData.Failure(message = "No internet connection available, please try again!")
    val unexpectedFailure =
        StateData.Failure(message = "Sorry, an unexpected error has occurred, please try again!")
    val notFoundShowsFailure =
        StateData.Failure(message = "The search you made did not found any TV Show, try fixing your search!")
    val getEpisodesFailure =
        StateData.Failure(message = "Sorry, an unexpected error has occurred while fetching this TV Show episodes, please try again!")
}