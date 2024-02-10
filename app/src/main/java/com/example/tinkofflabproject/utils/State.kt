package com.example.tinkofflabproject.utils

sealed class State<T : Any>{
    class Loading<T : Any> : State<T>()
    data class Error<T : Any>(
        val throwable: Throwable
    ) : State<T>()
    data class Success<T : Any>(
        val data : T
    ) : State<T>()
}
