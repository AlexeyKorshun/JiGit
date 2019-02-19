/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.di

import android.content.Context
import com.rosberry.mpp.jigitbl.data.auth.AuthApi
import com.rosberry.mpp.jigitbl.data.auth.AuthManager
import com.rosberry.mpp.jigitbl.data.auth.AuthRepository
import com.rosberry.mpp.jigitbl.domain.AuthInteractor
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
        bind(AuthManager::class.java).toProvider(AuthManagerProvider::class.java)
        bind(HttpClient::class.java).toProvider(HttpClientProvider::class.java)
        bind(AuthApi::class.java).toProvider(AuthApiProvider::class.java)
        bind(AuthRepository::class.java).toProvider(AuthRepositoryProvider::class.java)
        bind(AuthInteractor::class.java).toProvider(AuthInteractorProvider::class.java)

        //Navigation
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    }
}