package com.food.orderfood.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.food.orderfood.R
import com.food.orderfood.model.MenuItem
import kotlinx.android.synthetic.main.item_makanan.view.*

class MenuReyclerViewAdapter(
    val itemListMenu: List<MenuItem>
) : RecyclerView.Adapter<MenuReyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_makanan, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemListMenu[position]

        holder.itemView.apply {
            tv_item_nama.text = currentItem.namaMenu
            Glide.with(context).load(currentItem.gambar).into(iv_item_gambar)
            setOnClickListener {
                onItemClickListener?.let {
                    it(currentItem)
                }
            }
        }
    }

    private var onItemClickListener: ((MenuItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (MenuItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = itemListMenu.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}