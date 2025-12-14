package com.ejemplo.expensetrackerpro.utils

import android.content.Context
import com.ejemplo.expensetrackerpro.model.Expense
import java.io.File
import java.io.IOException

class FileHelper(private val context: Context) {

    private val fileName = "gastos_db.txt"

    // Guardar lista completa (sobrescribe el archivo para mantener sincronía)
    fun saveExpenses(expenses: List<Expense>) {
        try {
            val file = File(context.filesDir, fileName)
            val fileContent = StringBuilder()
            
            expenses.forEach { expense ->
                fileContent.append(expense.toCsvString()).append("\n")
            }
            
            file.writeText(fileContent.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // Agregar un solo gasto al final del archivo (más eficiente)
    fun appendExpense(expense: Expense) {
        try {
            val file = File(context.filesDir, fileName)
            file.appendText(expense.toCsvString() + "\n")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // Leer todos los gastos del txt
    fun readExpenses(): MutableList<Expense> {
        val expenseList = mutableListOf<Expense>()
        val file = File(context.filesDir, fileName)

        if (!file.exists()) return expenseList

        try {
            file.forEachLine { line ->
                if (line.isNotBlank()) {
                    Expense.fromCsvString(line)?.let { expenseList.add(it) }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return expenseList
    }
    
    // Función Admin: Leer el archivo crudo como String
    fun readRawFile(): String {
        val file = File(context.filesDir, fileName)
        return if (file.exists()) file.readText() else "Archivo vacío o inexistente."
    }
}
