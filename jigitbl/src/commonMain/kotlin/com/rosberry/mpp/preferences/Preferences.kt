/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.preferences

/**
 * @author Alexei Korshun on 06/02/2019.
 */
interface Preferences {

    fun getBoolean(key: String, defaultValue: Boolean): Boolean
    fun getBoolean(key: String): Boolean = getBoolean(key, false)
    fun putBoolean(key: String, value: Boolean)

    fun getString(key: String, defaultValue: String): String
    fun getString(key: String): String = getString(key, "")
    fun putString(key: String, value: String)
    fun clear()

    interface Factory {
        fun create(name: String): Preferences
    }
}
