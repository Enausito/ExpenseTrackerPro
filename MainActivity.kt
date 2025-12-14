package com.ejemplo.expensetrackerpro

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ejemplo.expensetrackerpro.adapter.ExpenseAdapter
import com.ejemplo.expensetrackerpro.databinding.ActivityMainBinding
import com.ejemplo.expensetrackerpro.model.Expense
import com.ejemplo.expensetrackerpro.utils.FileHelper
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    // Variables globales de la clase
    private lateinit var binding: ActivityMainBinding
    private lateinit var fileHelper: FileHelper
    private lateinit var adapter: ExpenseAdapter
    private var expenseList = mutableListOf<Expense>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Configuración de ViewBinding (Conecta el código con el diseño XML)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Inicializar herramientas de archivo (Base de datos TXT)
        fileHelper = FileHelper(this)

        // 2. Configurar la lista visual (RecyclerView)
        setupRecyclerView()

        // 3. Configurar el botón flotante para ir a "Agregar Gasto"
        binding.fabAdd.setOnClickListener {
             val intent = Intent(this, AddExpenseActivity::class.java)
             startActivity(intent)
        }
    }

    // Este método se ejecuta siempre que la pantalla se vuelve visible
    // (Al abrir la app o al regresar de la pantalla "Agregar Gasto")
    override fun onResume() {
        super.onResume()
        loadExpenses()
    }

    // --- MENÚ SUPERIOR (ADMIN) ---
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflamos el archivo main_menu.xml que creamos anteriormente
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_admin -> {
                // Navegar a la pantalla de Administrador
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // ----------------------------

    private fun setupRecyclerView() {
        adapter = ExpenseAdapter(expenseList)
        binding.rvExpenses.layoutManager = LinearLayoutManager(this)
        binding.rvExpenses.adapter = adapter
    }

    private fun loadExpenses() {
        // 1. Leer todos los datos del archivo TXT
        expenseList = fileHelper.readExpenses()
        
        // 2. Ordenar por fecha (el más reciente arriba)
        expenseList.sortByDescending { it.date }

        // 3. Actualizar el adaptador de la lista
        adapter.updateData(expenseList)

        // 4. Recalcular el saldo total
        updateTotalBalance()
        
        // 5. Mostrar/Ocultar el texto de "Lista vacía"
        binding.tvEmptyState.visibility = if (expenseList.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun updateTotalBalance() {
        // Suma todos los montos de la lista
        val total = expenseList.sumOf { it.amount }
        
        // Formatea el número a moneda local (ej: $1,200.50)
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        binding.tvTotalBalance.text = format.format(total)
    }
}
