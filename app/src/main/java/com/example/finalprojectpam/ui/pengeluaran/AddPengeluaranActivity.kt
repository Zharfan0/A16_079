package com.example.finalprojectpam.ui.pengeluaran

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectpam.R
import com.example.finalprojectpam.data.model.Pengeluaran
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddPengeluaranActivity : AppCompatActivity() {

    private lateinit var etNamaPengeluaran: EditText
    private lateinit var etJumlahPengeluaran: EditText
    private lateinit var btnSimpanPengeluaran: Button
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pengeluaran)

        etNamaPengeluaran = findViewById(R.id.etNamaPengeluaran)
        etJumlahPengeluaran = findViewById(R.id.etJumlahPengeluaran)
        btnSimpanPengeluaran = findViewById(R.id.btnSimpanPengeluaran)

        btnSimpanPengeluaran.setOnClickListener {
            val namaPengeluaran = etNamaPengeluaran.text.toString()
            val jumlahPengeluaran = etJumlahPengeluaran.text.toString().toDoubleOrNull()

            if (namaPengeluaran.isNotEmpty() && jumlahPengeluaran != null) {
                val pengeluaran = Pengeluaran(
                    idPengeluaran = db.collection("pengeluaran").document().id,
                    idAset = "", // Kosongkan jika tidak ada kaitan dengan aset
                    idKategori = "", // Kosongkan jika tidak ada kaitan dengan kategori
                    tanggalTransaksi = Date(),
                    total = jumlahPengeluaran,
                    catatan = namaPengeluaran
                )

                db.collection("pengeluaran")
                    .document(pengeluaran.idPengeluaran)
                    .set(pengeluaran)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Pengeluaran berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Gagal menambahkan pengeluaran: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Semua bidang harus diisi dengan benar!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
