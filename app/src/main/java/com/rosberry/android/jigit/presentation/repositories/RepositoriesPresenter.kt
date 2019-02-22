/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.presentation.repositories

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.rosberry.mpp.jigitbl.domain.RepositoriesInteractor
import com.rosberry.mpp.jigitbl.entity.Repository
import javax.inject.Inject

/**
 * @author Alexei Korshun on 22/02/2019.
 */
@InjectViewState
class RepositoriesPresenter @Inject constructor(
        private val repositoriesInteractor: RepositoriesInteractor
) : MvpPresenter<RepositoriesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showProgress(true)
        repositoriesInteractor.getRepositories(this::onReceiveRepositories, this::onError)
    }

    private fun onReceiveRepositories(repositories: List<Repository>) {
        viewState.showError(false)
        viewState.showProgress(false)
        viewState.showRepositories(repositories)
    }

    private fun onError(exception: Throwable) {
        exception.printStackTrace()
        viewState.showProgress(false)
        viewState.showError(true, exception.localizedMessage)
    }
}