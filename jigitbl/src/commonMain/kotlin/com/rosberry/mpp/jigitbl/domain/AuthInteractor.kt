/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.domain

import com.rosberry.mpp.jigitbl.data.auth.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author Alexei Korshun on 06/02/2019.
 */
class AuthInteractor(
        private val authRepository: AuthRepository
) {

    fun isAuth(): Boolean = authRepository.username.isNotBlank() && authRepository.password.isNotBlank()

    fun isValidCredentials(username: String, password: String): Boolean =
            username.isNotBlank() && password.isNotBlank()

    fun auth(username: String, password: String, onResult: (String) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                val result: String = authRepository.auth(username, password)
                onResult.invoke(result)
            }
        }
    }
}

internal expect val ApplicationDispatcher: CoroutineDispatcher