package com.food.orderfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.food.orderfood.model.User
import com.food.orderfood.util.Constant.BASE_URL
import com.windi.foodorder.model.User
import com.windi.foodorder.utill.Constant.BASE_URL
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "$BASE_URL/user/login"

            val jsonObject = JSONObject()
            jsonObject.apply {
                put("email", txEmail.text.toString())
                put("password", txPassword.text.toString())
            }

            val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.POST, url, jsonObject,
                    { json ->
                        val user = json.getJSONObject("data")

                        if (user.toString() != "null") {
                            Toast.makeText(this, "Berhasil masuk", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, profil::class.java)
                            val dataUser =
                                    User(
                                            id = user.getString("id_user"),
                                            email = user.getString("email"),
                                            nama = user.getString("name")
                                    )
                            intent.apply {
                                putExtra(profil.EXTRA_PARCEL_USER, dataUser)
                            }
                            finish()
                            startActivity(intent)
                        }
                    }, {
                Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
            }
            )

            queue.add(jsonObjectRequest)
        }

        tvCreateAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}