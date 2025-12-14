package com.ejemplo.expensetrackerpro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ejemplo.expensetrackerpro.databinding.ItemExpenseBinding
import com.ejemplo.expensetrackerpro.model.Expense
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

class ExpenseAdapter(private var expenseList: List<Expense>) :
    RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    // Formateadores para que se vea bonito (Moneda y Fecha)
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    inner class ExpenseViewHolder(val binding: ItemExpenseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val item = expenseList[position]
        with(holder.binding) {
            tvCategory.text = item.category
            tvNote.text = item.note
            tvAmount.text = currencyFormat.format(item.amount)
            tvDate.text = dateFormat.format(item.date)
        }
    }

    override fun getItemCount(): Int = expenseList.size

    // Función para actualizar la lista cuando agregamos datos nuevos
    fun updateData(newList: List<Expense>) {
        expenseList = newList
        notifyDataSetChanged()
    }
}
