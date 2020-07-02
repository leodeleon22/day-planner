package com.leodeleon.data.local

import splitties.experimental.ExperimentalSplittiesApi
import splitties.preferences.Preferences

@ExperimentalSplittiesApi
internal object AppPreferences : Preferences("app_preferences") {
    var token by stringPref("")
}