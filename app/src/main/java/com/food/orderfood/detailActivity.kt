package com.food.orderfood


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.food.orderfood.model.MenuItem
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class detailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PARCEL_MENUITEM = "extra_parcel_menuitem"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val menuItem: MenuItem? = intent.extras?.getParcelable(EXTRA_PARCEL_MENUITEM)

        menuItem?.let { item ->
            Glide.with(this).load(item.gambar).into(detail_cover)
            detail_judul.text = item.namaMenu
            detail_harga.text = "Rp. ${item.harga}"
        }

        btn_tambah.setOnClickListener {
            var jumlah = detail_jumlah.text.toString().toInt()
            jumlah++
            detail_jumlah.setText("${jumlah}")
        }

        btn_kurang.setOnClickListener {
            var jumlah = detail_jumlah.text.toString().toInt()

            if (jumlah > 0) {
                jumlah--
                detail_jumlah.setText("${jumlah}")
            }
        }

        btnPesan.setOnClickListener {
            Toast.makeText(
                    this,
                    "Terimakasih, Pesanan Anda di Proses. \nTotal Harga : Rp.${
                        menuItem?.harga?.times(detail_jumlah.text.toString().toInt())
                    }",
                    Toast.LENGTH_LONG
            ).show()

            GlobalScope.launch {
                delay(1500L)
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}