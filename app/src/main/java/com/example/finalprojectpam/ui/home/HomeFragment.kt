package com.example.finalprojectpam.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.finalprojectpam.R
import com.example.finalprojectpam.R.id.tvTotalPendapatan
import com.example.finalprojectpam.R.id.tvTotalPengeluaran

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var tvSaldo: TextView
    private lateinit var tvTotalPendapatan: TextView
    private lateinit var tvTotalPengeluaran: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Inisialisasi view
        tvSaldo = view.findViewById(R.id.tvSaldo)
        tvTotalPendapatan = view.findViewById(R.id.tvTotalPendapatan)
        tvTotalPengeluaran = view.findViewById(R.id.tvTotalPengeluaran)

        // Observasi data dari ViewModel
        homeViewModel.saldo.observe(viewLifecycleOwner) { saldo ->
            tvSaldo.text = "Saldo: Rp$saldo"
        }

        homeViewModel.totalPendapatan.observe(viewLifecycleOwner) { totalPendapatan ->
            tvTotalPendapatan.text = "Total Pendapatan: Rp$totalPendapatan"
        }

        homeViewModel.totalPengeluaran.observe(viewLifecycleOwner) { totalPengeluaran ->
            tvTotalPengeluaran.text = "Total Pengeluaran: Rp$totalPengeluaran"
        }

        return view
    }
}
