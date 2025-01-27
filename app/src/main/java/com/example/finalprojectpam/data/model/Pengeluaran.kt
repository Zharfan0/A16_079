package com.example.finalprojectpam.data.model

import java.util.Date

data class Pengeluaran(
    val idPengeluaran: String = "",
    val idAset: String = "",
    val idKategori: String = "",
    val tanggalTransaksi: Date = Date(),
    val total: Double = 0.0,
    val catatan: String = ""
)