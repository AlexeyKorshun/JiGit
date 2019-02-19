/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * @author Alexei Korshun on 19/02/2019.
 */
internal class MainScope: CoroutineScope {
    private val dispatcher = MainDispatcher
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatcher + job
}