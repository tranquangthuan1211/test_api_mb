package com.example.call_api

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.call_api.api.ApiManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val submitButton = findViewById<Button>(R.id.submit)
        submitButton.setOnClickListener {
            testApi()
//            submitForm()
        }
    }


    private fun testApi() {
        ApiManager.getApiManager().testApi(
            onSuccess = { response ->
                if (response != null) {
                    Toast.makeText(this, response.data.toString(), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Không thành công", Toast.LENGTH_SHORT).show()
                }
            },
            onError = { errorMessage ->
                Log.e("Login", "Error: $errorMessage")
                Toast.makeText(this, "Không thành công", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
