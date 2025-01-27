package com.example.finalprojectpam.ui.kategori

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectpam.R
import com.example.finalprojectpam.data.model.Kategori
import com.google.firebase.firestore.FirebaseFirestore

class AddKategoriActivity : AppCompatActivity() {

    private lateinit var etNamaKategori: EditText
    private lateinit var etDeskripsiKategori: EditText
    private lateinit var btnSimpanKategori: Button
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_kategori)

        etNamaKategori = findViewById(R.id.etNamaKategori)
        etDeskripsiKategori = findViewById(R.id.etDeskripsiKategori)
        btnSimpanKategori = findViewById(R.id.btnSimpanKategori)

        btnSimpanKategori.setOnClickListener {
            val namaKategori = etNamaKategori.text.toString()
            val deskripsiKategori = etDeskripsiKategori.text.toString()

            if (namaKategori.isNotEmpty() && deskripsiKategori.isNotEmpty()) {
                val kategori = Kategori(
                    idKategori = db.collection("kategori").document().id,
                    namaKategori = namaKategori,
                    deskripsi = deskripsiKategori
                )

                db.collection("kategori")
                    .document(kategori.idKategori)
                    .set(kategori)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Kategori berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Gagal menambahkan kategori: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Semua bidang harus diisi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
