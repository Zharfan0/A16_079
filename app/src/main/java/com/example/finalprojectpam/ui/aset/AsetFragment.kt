package com.example.finalprojectpam.ui.aset

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
import com.example.finalprojectpam.data.model.Aset
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class AsetFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddAset: FloatingActionButton
    private val db = FirebaseFirestore.getInstance()
    private val asetList = mutableListOf<Aset>()
    private lateinit var asetAdapter: AsetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_aset, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewAset)
        fabAddAset = view.findViewById(R.id.fabAddAset)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        asetAdapter = AsetAdapter(asetList)
        recyclerView.adapter = asetAdapter

        fabAddAset.setOnClickListener {
            // Pindah ke AddAsetActivity
            startActivity(Intent(requireContext(), AddAsetActivity::class.java))
        }

        fetchAset()
        return view
    }

    private fun fetchAset() {
        db.collection("aset")
            .get()
            .addOnSuccessListener { documents ->
                asetList.clear()
                for (document in documents) {
                    val aset = document.toObject(Aset::class.java)
                    asetList.add(aset)
                }
                asetAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Gagal memuat data aset: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
