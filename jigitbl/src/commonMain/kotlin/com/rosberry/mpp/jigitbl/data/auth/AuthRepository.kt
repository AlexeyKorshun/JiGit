/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.data.auth

import com.rosberry.mpp.jigitbl.entity.User

/**
 * @author Alexei Korshun on 08/02/2019.
 */
class AuthRepository(
        private val authManager: AuthManager,
        private val authApi: AuthApi
) {

    var username: String
        set(value) {
            authManager.username = value
        }
        get() = authManager.username
    var password: String
        set(value) {
            authManager.password = value
        }
        get() = authManager.password

    suspend fun auth(username: String, password: String): User = authApi.auth(username, password)
}