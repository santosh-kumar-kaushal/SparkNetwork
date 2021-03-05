package com.santosh.sparknetwork.util

import com.santosh.sparknetwork.domain.model.SparkNetwork

sealed class UIState

object DefaultState : UIState()
object LoadingState : UIState()
data class ErrorState(internal val errorMessage: String) : UIState()
data class RetrievedPostState(internal val sparkNetwork: SparkNetwork) : UIState()