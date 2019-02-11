/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.mpp.jigitbl.data.auth

import io.ktor.client.HttpClient
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.Url

/**
 * @author Alexei Korshun on 08/02/2019.
 */
class AuthApi {

    private val client = HttpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
    private val signInUrl = Url("https://api.github.com/user")

    suspend fun auth(username: String, password: String): String {
        val credentials: String = "$username:$password"

		val token: String = "Basic " + credentials.base64encoded()

        val builder: HttpRequestBuilder = HttpRequestBuilder()

        with(builder) {
            header("Authorization", token)
        }
        return client.get(signInUrl)
    }
}

private const val BASE64_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
private val RX_BASE64_CLEANR = "[^=" + BASE64_SET + "]".toRegex()
fun String.base64encoded(): String {
        val pad = when (this.length % 3) {
            1 -> "=="
            2 -> "="
            else -> ""
        }

        val raw = this + 0.toChar().toString().repeat(minOf(0, pad.lastIndex))

        return raw.chunkedSequence(3) {
            Triple(
                    it[0].toInt(),
                    it[1].toInt(),
                    it[2].toInt()
            )
        }.map { (frst, scnd, thrd) ->
            (0xFF.and(frst) shl 16) +
                    (0xFF.and(scnd) shl 8) +
                    0xFF.and(thrd)
        }.map { n ->
            sequenceOf(
                    (n shr 18) and 0x3F,
                    (n shr 12) and 0x3F,
                    (n shr 6) and 0x3F,
                    n and 0x3F
            )
        }.flatten()
            .map { BASE64_SET[it] }
            .joinToString("")
            .dropLast(pad.length) + pad
    }