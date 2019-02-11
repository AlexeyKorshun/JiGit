/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.preferences

import android.content.Context
import android.content.SharedPreferences

/**
 * @author Alexei Korshun on 06/02/2019.
 */

actual class PlatformPreferences(
        private val delegate: SharedPreferences
) : Preferences {

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return delegate.getBoolean(key, defaultValue)
    }

    override fun putBoolean(key: String, value: Boolean) {
        delegate.edit()
            .putBoolean(key, value)
            .apply()
    }

    override fun clear() {
        delegate.edit()
            .clear()
            .apply()
    }

    actual class Factory(
            private val context: Context
    ) : Preferences.Factory {

        override fun create(name: String): Preferences {
            val prefs = context.getSharedPreferences(name, Context.MODE_PRIVATE)
            return PlatformPreferences(prefs)
        }

    }
}
