/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.di

import android.content.Context
import com.rosberry.mpp.delight.SqlDriverFactory
import com.rosberry.mpp.jigitbl.data.auth.AuthApi
import com.rosberry.mpp.jigitbl.data.auth.AuthManager
import com.rosberry.mpp.jigitbl.data.auth.AuthRepository
import com.rosberry.mpp.jigitbl.data.repositories.RepositoriesRepository
import com.rosberry.mpp.jigitbl.data.repositories.RepositoryApi
import com.rosberry.mpp.jigitbl.data.repositories.RepositoryDb
import com.rosberry.mpp.jigitbl.domain.AuthInteractor
import com.rosberry.mpp.jigitbl.domain.RepositoriesInteractor
import com.rosberry.mpp.ktor.KtorFactory
import com.rosberry.mpp.network.NetworkManagerFactory
import com.rosberry.mpp.preferences.PlatformPreferences
import io.ktor.client.HttpClient
import javax.inject.Inject
import javax.inject.Provider

/**
 * @author Alexei Korshun on 11/02/2019.
 */
internal class HttpClientProvider @Inject constructor() : Provider<HttpClient> {

    override fun get(): HttpClient = KtorFactory().createKtor()
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

internal class RepositoriesApiProvider @Inject constructor(
        private val httpClient: HttpClient,
        private val authManager: AuthManager
) : Provider<RepositoryApi> {

    override fun get(): RepositoryApi = RepositoryApi(httpClient, authManager)
}

internal class SqlDriverFactoryProvider @Inject constructor(
        private val context: Context
) : Provider<SqlDriverFactory> {

    override fun get(): SqlDriverFactory {
        return SqlDriverFactory(context)
    }
}

internal class RepositoryDbProvider @Inject constructor(
        private val sqlDriverProvider: SqlDriverFactory
) : Provider<RepositoryDb> {

    override fun get(): RepositoryDb {
        return RepositoryDb(sqlDriverProvider)
    }
}

internal class RepositoriesRepositoryProvider @Inject constructor(
        private val networkManagerFactory: NetworkManagerFactory,
        private val repositoriesApi: RepositoryApi,
        private val repositoryDb: RepositoryDb
) : Provider<RepositoriesRepository> {

    override fun get(): RepositoriesRepository = RepositoriesRepository(
            networkManagerFactory,
            repositoriesApi,
            repositoryDb
    )
}

internal class RepositoriesInteractorProvider @Inject constructor(
        private val repositoriesRepository: RepositoriesRepository
) : Provider<RepositoriesInteractor> {

    override fun get(): RepositoriesInteractor = RepositoriesInteractor(repositoriesRepository)
}