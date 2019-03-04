/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.data.auth

import com.rosberry.mpp.jigitbl.data.ApiHeaders
import com.rosberry.mpp.jigitbl.data.ApiUrls
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
        val token = "${ApiHeaders.BASIC} ${credentials.encodeBase64()}"

        val builder = HttpRequestBuilder()
        with(builder) {
            header(ApiHeaders.AUTHORIZATION, token)
            method = HttpMethod.Get
            url {
                it.protocol = URLProtocol.HTTPS
                it.host = ApiUrls.HOST
                it.path(ApiUrls.USER)
            }
        }

        return client.get(builder)
    }
}