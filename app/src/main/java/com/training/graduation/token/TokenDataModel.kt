package com.training.graduation.token

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class TokenRequest(
    val name: String,
    val email: String,
    val room: String,
    val isModerator: Boolean
)

data class TokenResponse(
    val token: String
)


interface TokenApi {
    @POST("/get-token")
    suspend fun getToken(@Body request: TokenRequest): Response<TokenResponse>
}
