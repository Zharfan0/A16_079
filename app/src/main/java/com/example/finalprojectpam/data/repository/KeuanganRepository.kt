package com.example.finalprojectpam.data.repository

import com.example.finalprojectpam.data.model.Aset
import com.example.finalprojectpam.data.model.Kategori
import com.example.finalprojectpam.data.model.Pendapatan
import com.example.finalprojectpam.data.model.Pengeluaran
import java.util.Date

class KeuanganRepository {

    private val asetList = mutableListOf<Aset>()
    private val kategoriList = mutableListOf<Kategori>()
    private val pendapatanList = mutableListOf<Pendapatan>()
    private val pengeluaranList = mutableListOf<Pengeluaran>()

    // Aset Management
    fun addAset(aset: Aset) {
        asetList.add(aset)
    }

    fun getAset(): List<Aset> {
        return asetList
    }

    fun getAsetById(idAset: String): Aset? {
        return asetList.find { it.idAset == idAset }
    }

    // Kategori Management
    fun addKategori(kategori: Kategori) {
        kategoriList.add(kategori)
    }

    fun getKategori(): List<Kategori> {
        return kategoriList
    }

    fun getKategoriById(idKategori: String): Kategori? {
        return kategoriList.find { it.idKategori == idKategori }
    }

    // Pendapatan Management
    fun addPendapatan(pendapatan: Pendapatan) {
        pendapatanList.add(pendapatan)
    }

    fun getPendapatan(): List<Pendapatan> {
        return pendapatanList
    }

    fun getPendapatanByDate(date: Date): List<Pendapatan> {
        return pendapatanList.filter { it.tanggalTransaksi == date }
    }

    // Pengeluaran Management
    fun addPengeluaran(pengeluaran: Pengeluaran) {
        pengeluaranList.add(pengeluaran)
    }

    fun getPengeluaran(): List<Pengeluaran> {
        return pengeluaranList
    }

    fun getPengeluaranByDate(date: Date): List<Pengeluaran> {
        return pengeluaranList.filter { it.tanggalTransaksi == date }
    }

    // Summary Management
    fun getTotalPendapatan(): Double {
        return pendapatanList.sumOf { it.total }
    }

    fun getTotalPengeluaran(): Double {
        return pengeluaranList.sumOf { it.total }
    }

    fun getSaldo(): Double {
        return getTotalPendapatan() - getTotalPengeluaran()
    }
}
