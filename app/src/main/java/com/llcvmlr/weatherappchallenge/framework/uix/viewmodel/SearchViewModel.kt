package com.llcvmlr.weatherappchallenge.framework.uix.viewmodel



import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llcvmlr.weatherappchallenge.base.location.LocationManager
import com.llcvmlr.weatherappchallenge.framework.domain.GetSearchContentsCountUseCase
import com.llcvmlr.weatherappchallenge.framework.domain.GetSearchContentsUseCase
import com.llcvmlr.weatherappchallenge.framework.model.WeatherResponse
import com.llcvmlr.weatherappchallenge.framework.uix.SearchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.llcvmlr.weatherappchallenge.network.Result
import com.llcvmlr.weatherappchallenge.util.ModelPreferencesManager
import com.llcvmlr.weatherappchallenge.util.WeatherConstant
import com.llcvmlr.weatherappchallenge.util.WeatherUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter

/**
 * A [ViewModel] responsible for handling the search functionality in the Weather App.
 *
 * This [ViewModel] manages the UI state related to weather search queries, including loading states,
 * search results, and caching. It interacts with use cases to fetch search results and maintains
 * the state of search queries and results.
 *
 * @property isLoading A [LiveData] object that indicates whether data is currently being loaded.
 * @property searchResults A [StateFlow] object that represents the current state of search results.
 * @property searchQuery A [StateFlow] object representing the current search query, managed by [SavedStateHandle].
 *
 * @param getSearchContentsUseCase A use case for fetching search results based on the query.
 * @param getSearchContentsCountUseCase A use case for getting the count of search contents.
 * @param savedStateHandle Handles saving and restoring the state of search queries.
 * @param weatherUtil A utility class for managing weather-related data and preferences.
 * @param appContext The application context used for accessing shared preferences and other application-wide resources.
 *
 * @constructor Creates an instance of [SearchViewModel] and initializes it with the provided dependencies.
 * Sets up a coroutine to listen to changes in the search query and fetches search results based on the query.
 * Handles loading, success, and error states.
 */
@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    getSearchContentsUseCase: GetSearchContentsUseCase,
    getSearchContentsCountUseCase: GetSearchContentsCountUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val weatherUtil: WeatherUtil,
    @ApplicationContext private val appContext: Context
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    /**
     * Sets the loading state to false.
     */
    fun stopLoading() {
        _isLoading.value = false
    }


    private val _searchResults = MutableStateFlow<SearchResultUiState>(SearchResultUiState.Loading)
    val searchResults: StateFlow<SearchResultUiState> get() = _searchResults

    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    init {
        loadLastSearchedCity()
        viewModelScope.launch {
            getSearchContentsCountUseCase()
                .flatMapLatest { totalCount ->
                    if (totalCount < SEARCH_MIN_FTS_ENTITY_COUNT) {
                        flowOf(SearchResultUiState.SearchNotReady)
                    } else {
                        searchQuery
                            .debounce(300)
                            .filter { it.isNotEmpty() }
                            .flatMapLatest { query ->
                            if (query.length < SEARCH_QUERY_MIN_LENGTH) {
                                flowOf(SearchResultUiState.EmptyQuery)
                            } else {
                                if (query.length == SEARCH_QUERY_MIN_LENGTH) {
                                    _isLoading.value = true
                                }
                                getSearchContentsUseCase(query)
                                    .map { result ->
                                        when (result) {
                                            is Result.Success -> {
                                                _isLoading.value = false
                                                saveCache(result.data)
                                                SearchResultUiState.Success(weatherResponse = result.data)
                                            }
                                            is Result.Loading -> {
                                                _isLoading.value = true
                                                SearchResultUiState.Loading
                                            }
                                            is Result.Error -> {
                                                _isLoading.value = false
                                                SearchResultUiState.LoadFailed
                                            }
                                        }
                                    }
                            }
                        }
                    }
                }
                .collect { result ->
                    _searchResults.value = result
                }
        }
    }

    private fun loadLastSearchedCity() {
        viewModelScope.launch {
            val lastSearchedCity: String? = weatherUtil.getCityName(appContext)
            if (!lastSearchedCity.isNullOrEmpty()) {
                val cachedWeatherData = weatherUtil.getSavingSchoolDataResponse()
                if (cachedWeatherData != null && cachedWeatherData.name == lastSearchedCity) {
                    _searchResults.value = SearchResultUiState.Success(weatherResponse = cachedWeatherData)
                } else {
                    _searchResults.value = SearchResultUiState.EmptyQuery
                }
            }
        }
    }

    /**
     * Updates the search query in [SavedStateHandle].
     *
     * @param query The new search query.
     */
    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    /**
     * Updates the search query in [SavedStateHandle] and triggers a search.
     *
     * @param query The new search query.
     */
    fun onSearchTriggered(query: String) {
        viewModelScope.launch {
            savedStateHandle[SEARCH_QUERY] = query
        }
    }

    /**
     * Requests location data if no city is currently searched.
     * Uses [LocationManager] to fetch location and weather data.
     *
     * @param locationManager The [LocationManager] used to check for permissions and fetch location data.
     */
    fun fetchLocation(locationManager: LocationManager) {
        val lastSearchedCity: String? = weatherUtil.getCityName(appContext)
        if (lastSearchedCity.isNullOrEmpty()) {
            _isLoading.value = true

            locationManager.checkForPermission(object : LocationManager.LocationCallback {
                override fun onLocationReceived(location: Location?, weatherResponse: WeatherResponse?) {
                    _isLoading.value = false
                    if (location != null && weatherResponse != null) {
                        saveCache(weatherResponse)
                        _searchResults.value = SearchResultUiState.Success(weatherResponse = weatherResponse)
                    }
                }
                override fun onPermissionDenied()  {
                    _isLoading.value = false
                }
            })
        }
    }

    /**
     * Saves the weather response to preferences and updates the city name.
     *
     * @param weatherResponse The [WeatherResponse] object to be cached.
     */
    fun saveCache(weatherResponse: WeatherResponse) {
        weatherUtil.saveCityName(appContext, weatherResponse.name)
        ModelPreferencesManager.put(weatherResponse, WeatherConstant.WEATHER_DATA_SAVING)
    }
}

private const val SEARCH_QUERY_MIN_LENGTH = 6 // Minimum length of the search query required to trigger a search.
private const val SEARCH_MIN_FTS_ENTITY_COUNT = 1 // Minimum count of entities required to perform a search.
private const val SEARCH_QUERY = "searchQuery" // Key used in [SavedStateHandle] for storing and retrieving the search query.