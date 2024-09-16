package com.llcvmlr.weatherappchallenge.framework.domain

import com.llcvmlr.weatherappchallenge.framework.data.SearchContentsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving the total count of search contents.
 *
 * This use case interacts with the [SearchContentsRepository] to get the count of search contents.
 */
class GetSearchContentsCountUseCase @Inject constructor(
    private val searchContentsRepository: SearchContentsRepository
) {

    /**
     * Executes the use case to retrieve the count of search contents.
     *
     * This operator function delegates the call to the [SearchContentsRepository]'s
     * `getSearchContentsCount` method, returning the count as a [Flow] of [Int].
     *
     * @return A [Flow] of [Int] representing the total count of search contents.
     */
    operator fun invoke(): Flow<Int> =
        searchContentsRepository.getSearchContentsCount()
}
