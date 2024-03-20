package com.denisijcu.galaxywatch5pro.presentation.models

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GPTApi {

    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer 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"
    )

    @POST("/v1/completions")
    fun getCompletion(
        @Body requestBody:GPTReq
    ): Call<GPTRes>
}