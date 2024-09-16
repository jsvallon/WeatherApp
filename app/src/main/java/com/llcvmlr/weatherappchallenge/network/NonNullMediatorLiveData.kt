package com.llcvmlr.weatherappchallenge.network

import androidx.lifecycle.MediatorLiveData

/**
 * A custom [MediatorLiveData] implementation that ensures the LiveData value is never null.
 *
 * This class extends [MediatorLiveData] and can be used to observe and manage non-null values
 * in a LiveData object. It provides the same functionality as [MediatorLiveData], but guarantees
 * that its value is always non-null, which can simplify handling of LiveData in your application.
 *
 * @param T The type of data held by this LiveData. It is ensured that the value will never be null.
 */
class NonNullMediatorLiveData<T> : MediatorLiveData<T>()
