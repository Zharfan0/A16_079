package com.example.finalprojectpam.ui.aset

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectpam.R
import com.example.finalprojectpam.data.model.Aset
import com.google.firebase.firestore.FirebaseFirestore

class AddAsetActivity : AppCompatActivity() {

    private lateinit var etNamaAset: EditText
    private lateinit var etKategoriAset: EditText
    private lateinit var btnSimpan: Button
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_aset)

        etNamaAset = findViewById(R.id.etNamaAset)
        etKategoriAset = findViewById(R.id.etKategoriAset)
        btnSimpan = findViewById(R.id.btnSimpan)

        btnSimpan.setOnClickListener {
            val namaAset = etNamaAset.text.toString()
            val kategoriAset = etKategoriAset.text.toString()

            if (namaAset.isNotEmpty() && kategoriAset.isNotEmpty()) {
                val aset = Aset(
                    idAset = db.collection("aset").document().id,
                    namaAset = namaAset,
                    kategori = kategoriAset
                )
                db.collection("aset")
                    .document(aset.idAset)
                    .set(aset)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Aset berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Gagal menambahkan aset: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Nama dan kategori harus diisi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
