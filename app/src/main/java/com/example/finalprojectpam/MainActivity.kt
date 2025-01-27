package com.example.finalprojectpam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.finalprojectpam.ui.aset.AsetFragment
import com.example.finalprojectpam.ui.home.HomeFragment
import com.example.finalprojectpam.ui.kategori.KategoriFragment
import com.example.finalprojectpam.ui.pendapatan.PendapatanFragment
import com.example.finalprojectpam.ui.pengeluaran.PengeluaranFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Set fragment default (HomeFragment)
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Handle navigation item selection
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuHome -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.menuPendapatan -> {
                    replaceFragment(PendapatanFragment())
                    true
                }
                R.id.menuPengeluaran -> {
                    replaceFragment(PengeluaranFragment())
                    true
                }
                R.id.menuAset -> {
                    replaceFragment(AsetFragment())
                    true
                }
                R.id.menuKategori -> {
                    replaceFragment(KategoriFragment())
                    true
                }
                else -> false
            }
        }
    }

    // Fungsi untuk mengganti fragment
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
