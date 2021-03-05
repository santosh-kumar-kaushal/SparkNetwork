package com.santosh.sparknetwork.util

sealed class UIState

object DefaultState : UIState()
object LoadingState : UIState()
data class ErrorState(internal val errorMessage: String) : UIState()
data class RetrievedPostState(internal val categories: List<String>) : UIState()