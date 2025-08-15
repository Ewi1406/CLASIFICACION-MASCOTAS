package com.tuuser.chandas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tuuser.chandas.adapters.ViewPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var btnMoreOptions: android.widget.ImageButton
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        btnMoreOptions = findViewById(R.id.btnMoreOptions)
        
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter
        
        // Configurar el botón de menú
        btnMoreOptions.setOnClickListener {
            showMoreOptionsMenu()
        }
        
        // Configurar iconos para las pestañas
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.setIcon(R.drawable.ic_home)
                    tab.text = "Inicio"
                }
                1 -> {
                    tab.setIcon(R.drawable.ic_paw)
                    tab.text = "Perfil"
                }
                else -> {
                    tab.setIcon(R.drawable.ic_home)
                    tab.text = "Inicio"
                }
            }
        }.attach()
    }
    

    
    private fun showMoreOptionsMenu() {
        val popup = android.widget.PopupMenu(this, btnMoreOptions)
        popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)
        
        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_contact -> {
                    viewPager.currentItem = 2
                    true
                }
                R.id.action_about -> {
                    showAboutDialog()
                    true
                }
                else -> false
            }
        }
        
        popup.show()
    }
    
    private fun showAboutDialog() {
        val message = """
            🐕 CHANDAS - App de Mascotas
            
            Versión: 1.0.0
            Desarrollado con ❤️ para amantes de mascotas
            
            Esta aplicación te permite:
            • Ver perfiles de mascotas
            • Explorar galerías de fotos
            • Contactar con nosotros
            
            ¡Gracias por usar CHANDAS!
        """.trimIndent()
        
        android.app.AlertDialog.Builder(this)
            .setTitle("Acerca De")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}