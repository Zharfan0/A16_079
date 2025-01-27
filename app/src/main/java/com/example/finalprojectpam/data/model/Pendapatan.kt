package com.example.finalprojectpam.data.model

import java.sql.Date

data class Pendapatan(
    val idPendapatan: Int,     // ID unik untuk pendapatan
    val idAset: Int,           // ID aset terkait
    val idKategori: Int,       // ID kategori terkait
    val tanggalTransaksi: Date, // Tanggal transaksi pendapatan
    val total: Double,         // Jumlah pendapatan
    val catatan: String        // Catatan tambahan
)