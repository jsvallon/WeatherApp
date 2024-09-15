package com.llcvmlr.weatherappchallenge.framework.domain



import com.llcvmlr.weatherappchallenge.framework.data.SearchContentsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * A use case which returns total count of *Fts tables
 */
class GetSearchContentsCountUseCase @Inject constructor(
    private val searchContentsRepository: SearchContentsRepository,
) {
    operator fun invoke(): Flow<Int> =
        searchContentsRepository.getSearchContentsCount()
}
