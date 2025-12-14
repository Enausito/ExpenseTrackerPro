package com.ejemplo.expensetrackerpro.model

import java.util.Date
import java.util.UUID

// Definimos la estructura de un gasto
data class Expense(
    val id: String = UUID.randomUUID().toString(),
    val amount: Double,
    val category: String,
    val note: String,
    val date: Long = System.currentTimeMillis()
) {
    // Convierte el objeto a una línea de texto CSV para guardar en el txt
    // Formato: ID|MONTO|CATEGORIA|NOTA|FECHA
    fun toCsvString(): String {
        return "$id|$amount|$category|$note|$date"
    }

    companion object {
        // Convierte una línea de texto del txt de vuelta a un objeto
        fun fromCsvString(csvLine: String): Expense? {
            val parts = csvLine.split("|")
            if (parts.size < 5) return null
            return Expense(
                id = parts[0],
                amount = parts[1].toDoubleOrNull() ?: 0.0,
                category = parts[2],
                note = parts[3],
                date = parts[4].toLongOrNull() ?: 0L
            )
        }
    }
}
