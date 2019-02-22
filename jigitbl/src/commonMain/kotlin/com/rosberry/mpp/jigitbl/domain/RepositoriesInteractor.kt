/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.domain

import com.rosberry.mpp.coroutines.MainScope
import com.rosberry.mpp.jigitbl.data.repositories.RepositoriesRepository
import com.rosberry.mpp.jigitbl.entity.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @author Alexei Korshun on 22/02/2019.
 */
class RepositoriesInteractor(
        private val repositoriesRepository: RepositoriesRepository
) {

    private var scope: CoroutineScope = MainScope()

    fun getRepositories(
            onResult: (List<Repository>) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        scope.launch {
            try {
                val result = repositoriesRepository.getMyRepositories()
                onResult.invoke(result)
            } catch (e: Exception) {
                onError.invoke(e)
            }
        }
    }
}