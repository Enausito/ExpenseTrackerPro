package com.example.expensetrackerpro.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertExpense(expense: Expense)

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT * FROM expenses WHERE category = :category")
    fun getExpensesByCategory(category: String): Flow<List<Expense>>
}