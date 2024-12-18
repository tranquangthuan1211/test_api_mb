package com.example.call_api.api

import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiHelper {
    private fun <T> parseResponse(responseBody: ResponseBody?, clazz: Class<T>): T? {
        return responseBody?.let {
            Gson().fromJson(it.string(), clazz)
        }
    }

    private fun <T> handleApiCall(
        call: Call<ResponseBody>,
        clazz: Class<T>,
        onSuccess: (T?) -> Unit,
        onError: (String?) -> Unit
    ) {
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    val result = parseResponse(response.body(), clazz)
                    onSuccess(result)
                } else {
                    onError(response.errorBody()?.string())
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                onError(t.message)
            }
        })
    }

    fun <T> executeRequest(
        method: String,
        path: String,
        headers: Map<String, String>,
        body: Any? = null,
        clazz: Class<T>,
        onSuccess: (T?) -> Unit,
        onError: (String?) -> Unit
    ) {
        val call = when (method.uppercase()) {
            "GET" -> RetrofitClient.apiService.getRequest(headers, path)
            "POST" -> RetrofitClient.apiService.postRequest(headers, path, body)
            "PUT" -> RetrofitClient.apiService.putRequest(headers, path, body)
            "DELETE" -> RetrofitClient.apiService.deleteRequest(headers, path)
            else -> throw IllegalArgumentException("Unsupported HTTP method: $method")
        }
        handleApiCall(call, clazz, onSuccess, onError)
    }
}