/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.preferences

import platform.Foundation.NSUserDefaults

/**
 * @author Alexei Korshun on 06/02/2019.
 */
actual class PlatformPreferences(
        private val delegate: NSUserDefaults
) : Preferences {

    override fun putBoolean(key: String, value: Boolean) {
        delegate.setBool(value, key)
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean =
            if (hasKey(key)) delegate.boolForKey(key) else defaultValue

    private fun hasKey(key: String): Boolean = delegate.objectForKey(key) != null

    override fun clear() {
        for (key in delegate.dictionaryRepresentation().keys) {
            remove(key as String)
        }
    }

    private fun remove(key: String): Unit = delegate.removeObjectForKey(key)

    actual class Factory : Preferences.Factory {

        override fun create(name: String): Preferences {
            val prefs = NSUserDefaults(name)
            return PlatformPreferences(prefs)
        }
    }
}
