package com.ejemplo.expensetrackerpro

import android.content.Intent
import android.os.Bundle
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

    private lateinit var binding: ActivityMainBinding
    private lateinit var fileHelper: FileHelper
    private lateinit var adapter: ExpenseAdapter
    private var expenseList = mutableListOf<Expense>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Inicializar herramientas
        fileHelper = FileHelper(this)
        
        // 2. Configurar RecyclerView
        setupRecyclerView()

        // 3. Botón flotante para agregar (Lo completaremos en Parte 3)
        binding.fabAdd.setOnClickListener {
             // val intent = Intent(this, AddExpenseActivity::class.java)
             // startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Recargar datos cada vez que volvemos a esta pantalla
        loadExpenses()
    }

    private fun setupRecyclerView() {
        adapter = ExpenseAdapter(expenseList)
        binding.rvExpenses.layoutManager = LinearLayoutManager(this)
        binding.rvExpenses.adapter = adapter
    }

    private fun loadExpenses() {
        // Leer del TXT
        expenseList = fileHelper.readExpenses()
        
        // Ordenar por fecha (más reciente primero)
        expenseList.sortByDescending { it.date }

        // Actualizar lista
        adapter.updateData(expenseList)

        // Actualizar Total
        updateTotalBalance()
        
        // Mostrar mensaje si está vacío
        binding.tvEmptyState.visibility = if (expenseList.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun updateTotalBalance() {
        val total = expenseList.sumOf { it.amount }
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        binding.tvTotalBalance.text = format.format(total)
    }
}
