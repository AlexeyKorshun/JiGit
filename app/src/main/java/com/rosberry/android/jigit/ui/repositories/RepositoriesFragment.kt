/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.ui.repositories

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rosberry.android.core.ui.AppFragment
import com.rosberry.android.jigit.R
import com.rosberry.android.jigit.di.Scopes
import com.rosberry.android.jigit.presentation.repositories.RepositoriesPresenter
import com.rosberry.android.jigit.presentation.repositories.RepositoriesView
import toothpick.Toothpick

/**
 * @author Alexei Korshun on 18/02/2019.
 */
class RepositoriesFragment : AppFragment(), RepositoriesView {

    override val layoutRes: Int
        get() = R.layout.fragment_repositories

    @InjectPresenter
    lateinit var presenter: RepositoriesPresenter

    @ProvidePresenter
    fun providePresenter(): RepositoriesPresenter {
        return Toothpick.openScopes(Scopes.APP, scopeName())
            .getInstance(RepositoriesPresenter::class.java)
    }
}