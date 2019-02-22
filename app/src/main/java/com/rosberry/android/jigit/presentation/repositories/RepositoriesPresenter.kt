/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.presentation.repositories

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.rosberry.mpp.jigitbl.domain.RepositoriesInteractor
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
        repositoriesInteractor.getRepositories(
                { repositories -> Log.d("REPOSITORIES: ", repositories.toString()) },
                { exception -> Log.e("REPOSITORIES: ", exception.localizedMessage, exception) }
        )
    }
}