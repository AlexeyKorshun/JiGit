/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.presentation.login

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author Alexei Korshun on 07/02/2019.
 */
interface LoginView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun isLoginEnable(isEnabled: Boolean)

    fun isLoading(isLoading: Boolean)

    fun isError(isError: Boolean, message: String = "")
}