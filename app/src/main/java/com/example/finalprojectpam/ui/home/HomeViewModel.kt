package com.example.finalprojectpam.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _saldo = MutableLiveData<Double>()
    val saldo: LiveData<Double> get() = _saldo

    private val _totalPendapatan = MutableLiveData<Double>()
    val totalPendapatan: LiveData<Double> get() = _totalPendapatan

    private val _totalPengeluaran = MutableLiveData<Double>()
    val totalPengeluaran: LiveData<Double> get() = _totalPengeluaran

    init {
        fetchFinancialData()
    }

    private fun fetchFinancialData() {
        // Fetch total pendapatan
        db.collection("pendapatan")
            .get()
            .addOnSuccessListener { documents ->
                val total = documents.sumOf { it.getDouble("total") ?: 0.0 }
                _totalPendapatan.value = total
                calculateSaldo()
            }

        // Fetch total pengeluaran
        db.collection("pengeluaran")
            .get()
            .addOnSuccessListener { documents ->
                val total = documents.sumOf { it.getDouble("total") ?: 0.0 }
                _totalPengeluaran.value = total
                calculateSaldo()
            }
    }

    private fun calculateSaldo() {
        val pendapatan = _totalPendapatan.value ?: 0.0
        val pengeluaran = _totalPengeluaran.value ?: 0.0
        _saldo.value = pendapatan - pengeluaran
    }
}
