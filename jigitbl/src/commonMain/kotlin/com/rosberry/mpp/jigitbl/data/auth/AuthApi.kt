/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.data.auth

import com.rosberry.mpp.jigitbl.entity.User
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64

/**
 * @author Alexei Korshun on 08/02/2019.
 */
class AuthApi(
        private val client: HttpClient
) {

    @UseExperimental(InternalAPI::class)
    suspend fun auth(username: String, password: String): User {
        val credentials = "$username:$password"

        val token: String = "Basic " + credentials.encodeBase64()

        val builder = HttpRequestBuilder()

        with(builder) {
            header("Authorization", token)
            method = HttpMethod.Get
            url {
                it.protocol = URLProtocol.HTTPS
                it.host = "api.github.com"
                it.path("user")
            }
        }

        return client.get(builder)
    }
}