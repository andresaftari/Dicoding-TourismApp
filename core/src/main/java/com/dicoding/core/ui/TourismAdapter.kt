package com.dicoding.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andresaftari.core.R
import com.bumptech.glide.Glide
import com.dicoding.core.domain.model.Tourism
import kotlinx.android.synthetic.main.item_list_tourism.view.*
import java.util.ArrayList

class TourismAdapter : RecyclerView.Adapter<TourismAdapter.ListViewHolder>() {
    // Mengubah tipe data TourismAdapter dari TourismEntity menjadi Tourism
    private var listData = ArrayList<Tourism>()
    var onItemClick: ((Tourism) -> Unit)? = null

    fun setData(newListData: List<Tourism>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_tourism, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    // Mengubah tipe data TourismAdapter dari TourismEntity menjadi Tourism
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Tourism) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(iv_item_image)

                tv_item_title.text = data.name
                tv_item_subtitle.text = data.address
            }
        }

        init {
            itemView.setOnClickListener { onItemClick?.invoke(listData[adapterPosition]) }
        }
    }
}