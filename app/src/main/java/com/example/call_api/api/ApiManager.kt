package com.example.call_api.api

import com.google.gson.annotations.SerializedName

data class TestRes(
    @SerializedName("data") val data: String? = null
)


class ApiManager {
    companion object {
        private val instance: ApiManager by lazy { ApiManager() }

        fun getApiManager(): ApiManager = instance
    }

    fun testApi(
        onSuccess: (TestRes?) -> Unit,
        onError: (String?) -> Unit
    ) {
        ApiHelper.executeRequest<TestRes>(
            method = "GET",
            path = "api/v1",
            headers = mapOf(
                "accept" to "application/json",
            ),
            clazz = TestRes::class.java,
            onSuccess = onSuccess,
            onError = onError
        )
    }

    // Đăng ký tài khoản
    fun register(
        postSignUpRequest: PostSignUpRequest,
        onSuccess: (PostSignUpResponse?) -> Unit,
        onError: (String?) -> Unit
    ) {
        ApiHelper.executeRequest<PostSignUpResponse>(
            method = "POST",
            path = "api/v1/users/register",
            headers = mapOf(
                "accept" to "application/json",
                "Content-Type" to "application/json; charset=UTF-8"
            ),
            body = postSignUpRequest,
            clazz = PostSignUpResponse::class.java,
            onSuccess = onSuccess,
            onError = onError
        )
    }

}
