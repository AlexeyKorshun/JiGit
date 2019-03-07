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
import com.rosberry.mpp.network.NetworkManagerFactory
import com.rosberry.mpp.preferences.PlatformPreferences
import io.ktor.client.HttpClient
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

/**
 * @author Alexei Korshun on 06/02/2019.
 */
class AppModule(context: Context) : Module() {

    init {

        bind(Context::class.java).toInstance(context)

        bind(PlatformPreferences.Factory::class.java).toInstance(PlatformPreferences.Factory(context))

        bind(HttpClient::class.java).toProvider(HttpClientProvider::class.java)

        bind(NetworkManagerFactory::class.java).toInstance(NetworkManagerFactory())

        bind(SqlDriverFactory::class.java).toProvider(SqlDriverFactoryProvider::class.java)
        bind(RepositoryDb::class.java).toProvider(RepositoryDbProvider::class.java)

        bind(AuthManager::class.java).toProvider(AuthManagerProvider::class.java)
        bind(AuthApi::class.java).toProvider(AuthApiProvider::class.java)
        bind(AuthRepository::class.java).toProvider(AuthRepositoryProvider::class.java)
        bind(AuthInteractor::class.java).toProvider(AuthInteractorProvider::class.java)

        bind(RepositoryApi::class.java).toProvider(RepositoriesApiProvider::class.java)
        bind(RepositoriesRepository::class.java).toProvider(RepositoriesRepositoryProvider::class.java)
        bind(RepositoriesInteractor::class.java).toProvider(RepositoriesInteractorProvider::class.java)

        //Navigation
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    }
}