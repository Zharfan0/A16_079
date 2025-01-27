package com.example.finalprojectpam.ui.pengeluaran

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
import com.example.finalprojectpam.data.model.Pengeluaran
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class PengeluaranFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddPengeluaran: FloatingActionButton
    private val db = FirebaseFirestore.getInstance()
    private val pengeluaranList = mutableListOf<Pengeluaran>()
    private lateinit var pengeluaranAdapter: PengeluaranAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pengeluaran, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewPengeluaran)
        fabAddPengeluaran = view.findViewById(R.id.fabAddPengeluaran)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        pengeluaranAdapter = PengeluaranAdapter(pengeluaranList)
        recyclerView.adapter = pengeluaranAdapter

        fabAddPengeluaran.setOnClickListener {
            startActivity(Intent(requireContext(), AddPengeluaranActivity::class.java))
        }

        fetchPengeluaran()
        return view
    }

    private fun fetchPengeluaran() {
        db.collection("pengeluaran")
            .get()
            .addOnSuccessListener { documents ->
                pengeluaranList.clear()
                for (document in documents) {
                    val pengeluaran = document.toObject(Pengeluaran::class.java)
                    pengeluaranList.add(pengeluaran)
                }
                pengeluaranAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Gagal memuat data pengeluaran: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
