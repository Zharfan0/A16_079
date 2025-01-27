package com.example.finalprojectpam.ui.aset

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectpam.R
import com.example.finalprojectpam.data.model.Aset

class AsetAdapter(private val asetList: List<Aset>) :
    RecyclerView.Adapter<AsetAdapter.AsetViewHolder>() {

    // ViewHolder untuk item RecyclerView
    class AsetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaAsetTextView: TextView = itemView.findViewById(R.id.tvNamaAset)
        val kategoriTextView: TextView = itemView.findViewById(R.id.tvKategori)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_aset, parent, false)
        return AsetViewHolder(view)
    }

    override fun onBindViewHolder(holder: AsetViewHolder, position: Int) {
        val aset = asetList[position]
        holder.namaAsetTextView.text = aset.namaAset
        holder.kategoriTextView.text = aset.kategori
    }

    override fun getItemCount(): Int {
        return asetList.size
    }
}
