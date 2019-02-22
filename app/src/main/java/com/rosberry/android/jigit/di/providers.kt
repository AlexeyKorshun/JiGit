/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.di

import com.rosberry.mpp.jigitbl.data.auth.AuthApi
import com.rosberry.mpp.jigitbl.data.auth.AuthManager
import com.rosberry.mpp.jigitbl.data.auth.AuthRepository
import com.rosberry.mpp.jigitbl.domain.AuthInteractor
import com.rosberry.mpp.preferences.PlatformPreferences
import io.ktor.client.HttpClient
import io.ktor.client.features.auth.Auth
import io.ktor.client.features.auth.providers.basic
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Provider

/**
 * @author Alexei Korshun on 11/02/2019.
 */
internal class HttpClientProvider @Inject constructor(
        private val authManager: AuthManager
) : Provider<HttpClient> {

    override fun get(): HttpClient {
        return HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json.nonstrict)
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
            install(Auth) {
                basic {
                    username = authManager.username
                    password = authManager.password
                }
            }
        }
    }
}

internal class AuthApiProvider @Inject constructor(
        private val httpClient: HttpClient
) : Provider<AuthApi> {

    override fun get(): AuthApi = AuthApi(httpClient)
}

internal class AuthManagerProvider @Inject constructor(
        private val factory: PlatformPreferences.Factory
) : Provider<AuthManager> {

    override fun get(): AuthManager = AuthManager(factory)
}

internal class AuthRepositoryProvider @Inject constructor(
        private val authManager: AuthManager,
        private val authApi: AuthApi
) : Provider<AuthRepository> {

    override fun get(): AuthRepository = AuthRepository(authManager, authApi)
}

internal class AuthInteractorProvider @Inject constructor(
        private val authRepository: AuthRepository
) : Provider<AuthInteractor> {

    override fun get(): AuthInteractor = AuthInteractor(authRepository)
}