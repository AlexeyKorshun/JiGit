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
        val isNetworkAvailable: Boolean = networkManager.isInternetAvailable()
        return if (isNetworkAvailable) fetchRepositoriesFromNetwork()
        else repositoryDb.getAllRepositories()
    }

    private suspend fun fetchRepositoriesFromNetwork(): List<Repository> {
        val result = repositoriesApi.getRepositories()
        repositoryDb.saveRepositories(result)
        return result
    }
}