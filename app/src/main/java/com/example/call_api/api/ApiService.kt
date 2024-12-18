package com.example.call_api.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Url


interface ApiService {
    @GET
    fun getRequest(
        @HeaderMap headers: Map<String, String>,
        @Url url: String
    ): Call<ResponseBody>

    @POST
    fun postRequest(
        @HeaderMap headers: Map<String, String>,
        @Url url: String,
        @Body body: Any? = null
    ): Call<ResponseBody>

    @PUT
    fun putRequest(
        @HeaderMap headers: Map<String, String>,
        @Url url: String,
        @Body body: Any? = null
    ): Call<ResponseBody>

    @DELETE
    fun deleteRequest(
        @HeaderMap headers: Map<String, String>,
        @Url url: String
    ): Call<ResponseBody>
}