/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.domain

import com.rosberry.mpp.jigitbl.data.auth.AuthRepository
import com.rosberry.mpp.jigitbl.entity.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * @author Alexei Korshun on 06/02/2019.
 */
class AuthInteractor(
        private val authRepository: AuthRepository
) {

    private var scope: CoroutineScope = InteractorScope()

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

internal expect val MainDispatcher: CoroutineDispatcher

internal class InteractorScope: CoroutineScope {
    private val dispatcher = MainDispatcher
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatcher + job
}