/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.data.auth

import com.rosberry.mpp.preferences.Preferences

/**
 * @author Alexei Korshun on 08/02/2019.
 */
class AuthManager constructor(
        private val factory: Preferences.Factory
) {

    companion object {
        private const val AUTH_STORE_NAME = "auth_preference_name"
        private const val KEY_USERNAME = "key_username"
        private const val KEY_PASSWORD = "key_password"
    }

    private val storage: Preferences by lazy { factory.create(AUTH_STORE_NAME) }

    var username: String
        set(value) = storage.putString(KEY_USERNAME, value)
        get() = storage.getString(KEY_USERNAME)

    var password: String
        set(value) = storage.putString(KEY_PASSWORD, value)
        get() = storage.getString(KEY_PASSWORD)
}