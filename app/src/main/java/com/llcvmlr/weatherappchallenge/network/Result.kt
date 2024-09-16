package com.llcvmlr.weatherappchallenge.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * A sealed interface representing the result of an operation.
 *
 * This interface is used to encapsulate the outcome of an operation, which can be either
 * successful, erroneous, or in a loading state. It provides a way to handle different
 * states of the operation in a structured manner.
 */
sealed interface Result<out T> {

    /**
     * Represents a successful result of an operation.
     *
     * @param data The data associated with the successful result.
     */
    data class Success<T>(val data: T) : Result<T>

    /**
     * Represents an error that occurred during the operation.
     *
     * @param exception The exception that caused the error.
     */
    data class Error(val exception: Throwable) : Result<Nothing>

    /**
     * Represents a loading state indicating that the operation is in progress.
     */
    data object Loading : Result<Nothing>
}

/**
 * Extension function that converts a [Flow] of type [T] into a [Flow] of [Result] objects.
 *
 * This function maps the values emitted by the original [Flow] into a [Result.Success] object,
 * emits a [Result.Loading] state at the start, and catches any exceptions to emit a
 * [Result.Error] state.
 *
 * @return A [Flow] of [Result] objects, where each value is either [Result.Success],
 *         [Result.Error], or [Result.Loading].
 */
fun <T> Flow<T>.asResult(): Flow<Result<T>> = map<T, Result<T>> { Result.Success(it) }
    .onStart { emit(Result.Loading) }
    .catch { emit(Result.Error(it)) }
