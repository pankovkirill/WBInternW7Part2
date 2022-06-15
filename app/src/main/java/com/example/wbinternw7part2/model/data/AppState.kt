package com.example.wbinternw7part2.model.data

sealed class AppState {
    data class Success(val dataModel: List<DataModel>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}