/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.data.repositories

import com.rosberry.mpp.jigitbl.data.ApiUrls
import com.rosberry.mpp.jigitbl.data.auth.AuthManager
import com.rosberry.mpp.jigitbl.entity.Repository
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64

/**
 * @author Alexei Korshun on 19/02/2019.
 */
class RepositoryApi(
        private val client: HttpClient,
        private val authManager: AuthManager
) {

    @UseExperimental(InternalAPI::class)
    suspend fun getRepositories(): List<Repository> {
        val credentials = "${authManager.username}:${authManager.password}"
        val token: String = "Basic " + credentials.encodeBase64()

        val builder = HttpRequestBuilder()
        with(builder) {
            header("Authorization", token)
            method = HttpMethod.Get
            url {
                it.protocol = URLProtocol.HTTPS
                it.host = ApiUrls.HOST
                it.path(ApiUrls.REPOSITORIES)
            }
        }

        return client.get(builder)
    }
}