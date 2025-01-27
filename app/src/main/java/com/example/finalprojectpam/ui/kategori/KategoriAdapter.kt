package com.example.finalprojectpam.ui.kategori

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectpam.R
import com.example.finalprojectpam.data.model.Kategori

class KategoriAdapter(private val kategoriList: List<Kategori>) :
    RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder>() {

    class KategoriViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaKategori: TextView = itemView.findViewById(R.id.tvNamaKategori)
        val deskripsiKategori: TextView = itemView.findViewById(R.id.tvDeskripsiKategori)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_kategori, parent, false)
        return KategoriViewHolder(view)
    }

    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        val kategori = kategoriList[position]
        holder.namaKategori.text = kategori.namaKategori
        holder.deskripsiKategori.text = kategori.deskripsi
    }

    override fun getItemCount(): Int = kategoriList.size
}
