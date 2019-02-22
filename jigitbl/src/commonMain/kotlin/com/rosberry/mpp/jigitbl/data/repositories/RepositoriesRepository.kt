/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.data.repositories

import com.rosberry.mpp.jigitbl.entity.Repository

/**
 * @author Alexei Korshun on 22/02/2019.
 */
class RepositoriesRepository(
        private val repositoriesApi: RepositoryApi
) {

    suspend fun getMyRepositories(): List<Repository> {
        return repositoriesApi.getRepositories()
    }
}