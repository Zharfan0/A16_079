package com.example.finalprojectpam.ui.pengeluaran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectpam.R
import com.example.finalprojectpam.data.model.Pengeluaran

class PengeluaranAdapter(private val pengeluaranList: List<Pengeluaran>) :
    RecyclerView.Adapter<PengeluaranAdapter.PengeluaranViewHolder>() {

    class PengeluaranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaPengeluaran: TextView = itemView.findViewById(R.id.tvNamaPengeluaran)
        val jumlahPengeluaran: TextView = itemView.findViewById(R.id.tvJumlahPengeluaran)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengeluaranViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pengeluaran, parent, false)
        return PengeluaranViewHolder(view)
    }

    override fun onBindViewHolder(holder: PengeluaranViewHolder, position: Int) {
        val pengeluaran = pengeluaranList[position]
        holder.namaPengeluaran.text = pengeluaran.catatan
        holder.jumlahPengeluaran.text = "Rp${pengeluaran.total}"
    }

    override fun getItemCount(): Int = pengeluaranList.size
}
