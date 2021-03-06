/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.domain

import com.rosberry.mpp.coroutines.MainScope
import com.rosberry.mpp.jigitbl.data.auth.AuthRepository
import com.rosberry.mpp.jigitbl.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @author Alexei Korshun on 06/02/2019.
 */
class AuthInteractor(
        private val authRepository: AuthRepository
) {

    private var scope: CoroutineScope = MainScope()

    fun isAuth(): Boolean = authRepository.username.isNotBlank() && authRepository.password.isNotBlank()

    fun isValidCredentials(username: String, password: String): Boolean =
            username.isNotBlank() && password.isNotBlank()

    fun auth(username: String, password: String, onSuccess: (User) -> Unit, onError: (Throwable) -> Unit) {
        scope.launch {
            try {
                val result: User = authRepository.auth(username, password)
                authRepository.username = username
                authRepository.password = password
                onSuccess.invoke(result)
            } catch (e: Throwable) {
                onError.invoke(e)
            }
        }
    }
}