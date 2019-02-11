/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.di

import com.rosberry.mpp.jigitbl.domain.AuthInteractor
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

/**
 * @author Alexei Korshun on 06/02/2019.
 */
class AppModule : Module() {

    init {

        bind(AuthInteractor::class.java).toInstance(AuthInteractor())

        //Navigation
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    }
}