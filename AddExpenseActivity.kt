package com.ejemplo.expensetrackerpro

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ejemplo.expensetrackerpro.databinding.ActivityAddExpenseBinding
import com.ejemplo.expensetrackerpro.model.Expense
import com.ejemplo.expensetrackerpro.utils.FileHelper

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddExpenseBinding
    private lateinit var fileHelper: FileHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fileHelper = FileHelper(this)

        setupSpinner()
        setupListeners()
    }

    private fun setupSpinner() {
        // Categorías hardcodeadas para simplicidad
        val categories = listOf("Comida", "Transporte", "Hogar", "Salud", "Ocio", "Otros")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
        binding.spinnerCategory.adapter = adapter
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            saveExpense()
        }
    }

    private fun saveExpense() {
        val amountStr = binding.etAmount.text.toString()
        val note = binding.etNote.text.toString()
        val category = binding.spinnerCategory.selectedItem.toString()

        if (amountStr.isEmpty()) {
            binding.tilAmount.error = "Ingresa un monto"
            return
        }

        val amount = amountStr.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            binding.tilAmount.error = "Monto inválido"
            return
        }

        // Crear objeto Gasto
        val expense = Expense(
            amount = amount,
            category = category,
            note = if (note.isEmpty()) "Sin nota" else note
        )

        // Guardar en TXT
        fileHelper.appendExpense(expense)

        Toast.makeText(this, "Gasto Guardado", Toast.LENGTH_SHORT).show()
        finish() // Cierra esta pantalla y vuelve al Dashboard
    }
}
