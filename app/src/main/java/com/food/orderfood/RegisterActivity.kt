package com.food.orderfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.food.orderfood.util.Constant
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "${Constant.BASE_URL}/user/register"

            val jsonObject = JSONObject()
            jsonObject.apply {
                put("name", txName.text.toString())
                put("email", txEmail.text.toString())
                put("password", txPasswordReg.text.toString())
            }

            val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.POST, url, jsonObject,
                    {
                        Toast.makeText(this, "Berhasil mendaftar", Toast.LENGTH_SHORT).show()

                        finish()
                    }, {
                Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
            }
            )

            queue.add(jsonObjectRequest)
        }

        tvRegister.setOnClickListener {
            finish()
        }
    }
}