package com.food.orderfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_kuy.*

class kuyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kuy)
        btnMulai.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}