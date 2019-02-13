/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.core.ui

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder

/**
 * @author Alexei Korshun on 07/02/2019.
 */
abstract class AppActivity : MvpAppCompatActivity() {

    abstract val navigatorHolder: NavigatorHolder
    abstract val navigator: Navigator

    protected abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) setContentView(layoutRes)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {

        val fragmentList = supportFragmentManager.fragments

        var handled = false
        for (fragment in fragmentList) {
            if (fragment is AppFragment) {
                handled = fragment.onBackPressed()
                if (handled) break
            }
        }

        if (!handled) {
            super.onBackPressed()
        }
    }
}