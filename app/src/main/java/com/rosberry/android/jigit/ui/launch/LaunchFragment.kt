/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.ui.launch

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rosberry.android.core.ui.AppFragment
import com.rosberry.android.jigit.R
import com.rosberry.android.jigit.di.Scopes
import com.rosberry.android.jigit.presentation.launch.LaunchPresenter
import toothpick.Toothpick

/**
 * @author Alexei Korshun on 06/02/2019.
 */
class LaunchFragment : AppFragment(), MvpView {

    override val layoutRes: Int
        get() = R.layout.fragment_launch

    @InjectPresenter
    lateinit var presenter: LaunchPresenter

    @ProvidePresenter
    fun providePresenter(): LaunchPresenter {
        return Toothpick.openScopes(Scopes.APP, scopeName())
            .getInstance(LaunchPresenter::class.java)
    }
}