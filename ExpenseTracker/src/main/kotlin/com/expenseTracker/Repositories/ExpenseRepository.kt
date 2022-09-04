package com.expenseTracker.Repositories

import com.expenseTracker.Entities.Entity.Expense
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Expense Repository for storage, retrieval update, delete and search operations on the Expense Entity.
 */
@Repository
interface ExpenseRepository : JpaRepository<Expense, Int> {
//    @Query("select E from Expense E where CAST (E.CategoryID AS String) like %?% ORDER BY Amount ASC")
//    fun getExpensesByCategoryID(CategoryID: Int?): List<Expense?>?
}