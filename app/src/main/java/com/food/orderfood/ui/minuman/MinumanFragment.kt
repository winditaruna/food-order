package com.food.orderfood.ui.minuman

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.food.orderfood.R
import com.food.orderfood.adapter.MenuReyclerViewAdapter
import com.food.orderfood.detailActivity
import com.food.orderfood.model.MenuItem
import com.food.orderfood.util.Constant
import kotlinx.android.synthetic.main.fragment_minuman.*

class MinumanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_minuman, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "${Constant.BASE_URL}/menu?jenis=minuman"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { json ->
                val responseFilms = json.getJSONArray("data")
                val listMenus = ArrayList<MenuItem>()

                for (i in 0 until responseFilms.length()) {
                    val item = responseFilms.getJSONObject(i)

                    val menuItem = MenuItem(
                        namaMenu = item.getString("nama_menu"),
                        harga = item.getInt("harga_menu"),
                        jenis = item.getString("jenis_menu"),
                        gambar = item.getString("gambar_menu")
                    )

                    listMenus.add(menuItem)
                }

                val adapter = MenuReyclerViewAdapter(listMenus)

                rv_minuman.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    this.adapter = adapter
                }

                adapter.apply {
                    setOnItemClickListener { currentItem ->
                        val intent = Intent(requireContext(), detailActivity::class.java)
                        intent.putExtra(detailActivity.EXTRA_PARCEL_MENUITEM, currentItem)

                        startActivity(intent)
                    }
                    notifyDataSetChanged()
                }

            }, {
                Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(jsonObjectRequest)
    }

}