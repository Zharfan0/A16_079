package com.example.finalprojectpam.ui.pendapatan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectpam.R
import com.example.finalprojectpam.data.model.Pendapatan
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class PendapatanFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddPendapatan: FloatingActionButton
    private val db = FirebaseFirestore.getInstance()
    private val pendapatanList = mutableListOf<Pendapatan>()
    private lateinit var pendapatanAdapter: PendapatanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pendapatan, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewPendapatan)
        fabAddPendapatan = view.findViewById(R.id.fabAddPendapatan)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        pendapatanAdapter = PendapatanAdapter(pendapatanList)
        recyclerView.adapter = pendapatanAdapter

        fabAddPendapatan.setOnClickListener {
            startActivity(Intent(requireContext(), AddPendapatanActivity::class.java))
        }

        fetchPendapatan()
        return view
    }

    private fun fetchPendapatan() {
        db.collection("pendapatan")
            .get()
            .addOnSuccessListener { documents ->
                pendapatanList.clear()
                for (document in documents) {
                    val pendapatan = document.toObject(Pendapatan::class.java)
                    pendapatanList.add(pendapatan)
                }
                pendapatanAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Gagal memuat data pendapatan: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
