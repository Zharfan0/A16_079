package com.example.finalprojectpam.ui.kategori

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
import com.example.finalprojectpam.data.model.Kategori
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class KategoriFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddKategori: FloatingActionButton
    private val db = FirebaseFirestore.getInstance()
    private val kategoriList = mutableListOf<Kategori>()
    private lateinit var kategoriAdapter: KategoriAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_kategori, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewKategori)
        fabAddKategori = view.findViewById(R.id.fabAddKategori)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        kategoriAdapter = KategoriAdapter(kategoriList)
        recyclerView.adapter = kategoriAdapter

        fabAddKategori.setOnClickListener {
            startActivity(Intent(requireContext(), AddKategoriActivity::class.java))
        }

        fetchKategori()
        return view
    }

    private fun fetchKategori() {
        db.collection("kategori")
            .get()
            .addOnSuccessListener { documents ->
                kategoriList.clear()
                for (document in documents) {
                    val kategori = document.toObject(Kategori::class.java)
                    kategoriList.add(kategori)
                }
                kategoriAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Gagal memuat data kategori: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
