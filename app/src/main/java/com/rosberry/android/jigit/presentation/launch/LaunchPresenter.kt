/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.presentation.launch

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.rosberry.android.jigit.Screens
import com.rosberry.mpp.jigitbl.domain.AuthInteractor
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Alexei Korshun on 06/02/2019.
 */
@InjectViewState
class LaunchPresenter @Inject constructor(
        private val authInteractor: AuthInteractor,
        private val router: Router
) : MvpPresenter<MvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (authInteractor.isAuth().not()) router.newRootScreen(Screens.LoginScreen())
        else router.newRootScreen(Screens.RepositoriesScreen())
    }
}