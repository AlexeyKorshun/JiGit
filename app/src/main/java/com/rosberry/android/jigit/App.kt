/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit

import android.app.Application
import com.rosberry.android.jigit.di.AppModule
import com.rosberry.android.jigit.di.Scopes
import toothpick.Toothpick

/**
 * @author Alexei Korshun on 06/02/2019.
 */
class App : Application() {

    override fun onCreate() {
        Toothpick.openScope(Scopes.APP)
            .installModules(AppModule())
        super.onCreate()
    }
}