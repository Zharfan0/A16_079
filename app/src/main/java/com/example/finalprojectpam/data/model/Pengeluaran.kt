package com.example.finalprojectpam.data.model

import java.util.Date

data class Pengeluaran(
    val idPengeluaran: Int,    // ID unik untuk pengeluaran
    val idAset: Int,           // ID aset terkait
    val idKategori: Int,       // ID kategori terkait
    val tanggalTransaksi: Date, // Tanggal transaksi pengeluaran
    val total: Double,         // Jumlah pengeluaran
    val catatan: String        // Catatan tambahan
)