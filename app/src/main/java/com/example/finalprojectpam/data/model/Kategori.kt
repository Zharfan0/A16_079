package com.example.finalprojectpam.data.model

data class Kategori(
    var idKategori: String = "",
    var namaKategori: String = "",
    var deskripsi: String = ""
) {
    // Constructor tanpa argumen
    constructor() : this("", "", "")
}
