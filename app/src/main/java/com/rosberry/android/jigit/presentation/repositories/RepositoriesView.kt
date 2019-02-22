/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.jigit.presentation.repositories

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author Alexei Korshun on 22/02/2019.
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoriesView : MvpView {
}