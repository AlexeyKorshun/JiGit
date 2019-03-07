/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.data.repositories

import com.rosberry.mpp.jigitbl.entity.Repository
import com.rosberry.mpp.network.NetworkManager
import com.rosberry.mpp.network.NetworkManagerFactory

/**
 * @author Alexei Korshun on 22/02/2019.
 */
class RepositoriesRepository(
        networkManagerFactory: NetworkManagerFactory,
        private val repositoriesApi: RepositoryApi,
        private val repositoryDb: RepositoryDb
) {

    private val networkManager: NetworkManager = networkManagerFactory.create()

    suspend fun getMyRepositories(): List<Repository> {
        return if (networkManager.isInternetAvailable()) repositoriesApi.getRepositories()
        else repositoryDb.getAllRepositories()
    }
}