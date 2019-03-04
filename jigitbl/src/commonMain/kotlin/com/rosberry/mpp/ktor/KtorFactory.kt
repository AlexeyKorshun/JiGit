/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.ktor

import com.rosberry.mpp.jigitbl.entity.Repository
import com.rosberry.mpp.jigitbl.entity.User
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

/**
 * @author Alexei Korshun on 22/02/2019.
 */
class KtorFactory {

    fun createKtor(): HttpClient {
        return HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json.nonstrict).apply {
                    register(Repository.serializer().list)
                    register(User.serializer())
                }
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }
}
