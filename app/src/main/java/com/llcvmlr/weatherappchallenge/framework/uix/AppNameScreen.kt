package com.llcvmlr.weatherappchallenge.framework.uix

import androidx.annotation.StringRes
import com.llcvmlr.weatherappchalleneg.R

/**
 * An enum class representing different screens in the weather application.
 *
 * Each enum constant corresponds to a specific screen in the application and holds a resource ID for the title of that screen.
 *
 * @property title A resource ID pointing to the string resource used as the title for the screen.
 *
 * @constructor Creates an instance of [AppNameScreen] with the specified title resource ID.
 *
 * @param title The resource ID for the screen's title.
 *
 * @see R.string
 */
enum class AppNameScreen(@StringRes val title: Int) {
    /**
     * Represents the search screen in the application.
     */
    SearchScreen(title = R.string.feature_search_title),
}
