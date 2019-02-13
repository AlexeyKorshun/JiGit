/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.domain

import com.rosberry.mpp.jigitbl.data.auth.AuthRepository
import com.rosberry.mpp.jigitbl.entity.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Alexei Korshun on 06/02/2019.
 */
class AuthInteractor(
        private val authRepository: AuthRepository
) {

    fun isAuth(): Boolean = authRepository.username.isNotBlank() && authRepository.password.isNotBlank()

    fun isValidCredentials(username: String, password: String): Boolean =
            username.isNotBlank() && password.isNotBlank()

    fun auth(username: String, password: String, onSuccess: (User) -> Unit, onError: (Throwable) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                try {
                    val result: User = withContext(Dispatchers.Default) {
                        authRepository.auth(username, password)
                    }
                    onSuccess.invoke(result)
                } catch (e: Throwable) {
                    onError.invoke(e)
                }
            }
        }
    }
}

internal expect val ApplicationDispatcher: CoroutineDispatcher