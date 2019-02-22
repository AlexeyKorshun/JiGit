/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.ui.repositories

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.rosberry.android.core.ui.AppFragment
import com.rosberry.android.extensions.show
import com.rosberry.android.jigit.R
import com.rosberry.android.jigit.di.Scopes
import com.rosberry.android.jigit.presentation.repositories.RepositoriesPresenter
import com.rosberry.android.jigit.presentation.repositories.RepositoriesView
import com.rosberry.mpp.jigitbl.entity.Repository
import kotlinx.android.synthetic.main.fragment_repositories.*
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

    override fun showProgress(isShow: Boolean) {
        showProgressDialog(isShow)
    }

    override fun showRepositories(repositories: List<Repository>) {
        repositoriesListView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        repositoriesListView.adapter = RepositoryAdapter(repositories)
        repositoriesListView.show(true)
    }

    override fun showError(isShow: Boolean, message: String) {
        errorMessageView.text = message
        errorMessageView.show(isShow)
    }
}