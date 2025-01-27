package com.example.finalprojectpam.ui.pendapatan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectpam.R
import com.example.finalprojectpam.data.model.Pendapatan
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddPendapatanActivity : AppCompatActivity() {

    private lateinit var etNamaPendapatan: EditText
    private lateinit var etJumlahPendapatan: EditText
    private lateinit var btnSimpanPendapatan: Button
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pendapatan)

        etNamaPendapatan = findViewById(R.id.etNamaPendapatan)
        etJumlahPendapatan = findViewById(R.id.etJumlahPendapatan)
        btnSimpanPendapatan = findViewById(R.id.btnSimpanPendapatan)

        btnSimpanPendapatan.setOnClickListener {
            val namaPendapatan = etNamaPendapatan.text.toString()
            val jumlahPendapatan = etJumlahPendapatan.text.toString().toDoubleOrNull()

            if (namaPendapatan.isNotEmpty() && jumlahPendapatan != null) {
                val pendapatan = Pendapatan(
                    idPendapatan = db.collection("pendapatan").document().id,
                    idAset = "", // Kosongkan jika tidak ada kaitan dengan aset
                    idKategori = "", // Kosongkan jika tidak ada kaitan dengan kategori
                    tanggalTransaksi = Date(),
                    total = jumlahPendapatan,
                    catatan = namaPendapatan
                )

                db.collection("pendapatan")
                    .document(pendapatan.idPendapatan)
                    .set(pendapatan)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Pendapatan berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Gagal menambahkan pendapatan: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Semua bidang harus diisi dengan benar!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
