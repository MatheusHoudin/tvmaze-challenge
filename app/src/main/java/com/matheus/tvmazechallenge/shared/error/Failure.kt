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
    val getPeopleFailure =
        StateData.Failure(message = "Sorry, an unexpected error has occurred while fetching people data, please try again!")
    val getCastCreditsFailure =
        StateData.Failure(message = "Sorry, an unexpected error has occurred while cast credits data, please try again!")
    val thereAreNoCastCreditsFailure =
        StateData.Failure(message = "No cast credits were found for this person!")
}