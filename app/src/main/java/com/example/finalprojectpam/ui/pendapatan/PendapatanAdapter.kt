package com.example.finalprojectpam.ui.pendapatan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectpam.R
import com.example.finalprojectpam.data.model.Pendapatan

class PendapatanAdapter(private val pendapatanList: List<Pendapatan>) :
    RecyclerView.Adapter<PendapatanAdapter.PendapatanViewHolder>() {

    class PendapatanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaPendapatan: TextView = itemView.findViewById(R.id.tvNamaPendapatan)
        val jumlahPendapatan: TextView = itemView.findViewById(R.id.tvJumlahPendapatan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendapatanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pendapatan, parent, false)
        return PendapatanViewHolder(view)
    }

    override fun onBindViewHolder(holder: PendapatanViewHolder, position: Int) {
        val pendapatan = pendapatanList[position]
        holder.namaPendapatan.text = pendapatan.catatan
        holder.jumlahPendapatan.text = "Rp${pendapatan.total}"
    }

    override fun getItemCount(): Int = pendapatanList.size
}
