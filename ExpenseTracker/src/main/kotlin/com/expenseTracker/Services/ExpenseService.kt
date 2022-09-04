package com.expenseTracker.Services

import com.expenseTracker.Entities.Entity.Expense
import com.expenseTracker.Repositories.ExpenseRepository
import com.expenseTracker.Repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ExpenseService (
    // Persistence layer for the Expense Service.
    @Autowired
    private val expenseRepository : ExpenseRepository,

    // Persistence layer for the Category Service.
    @Autowired
    private val categoryRepository : CategoryRepository
    ){

    // Get all Expenses.
    fun getAllExpenses(): List<Expense> {
        return expenseRepository.findAll()
    }

    //  Get Expense by id.
    fun getExpenseById(ExpenseID: Int): Expense? {
            if (ExpenseID <= 0)
                throw ResponseStatusException(HttpStatus.BAD_REQUEST)

            if(!expenseRepository.existsById(ExpenseID))
                throw ResponseStatusException(HttpStatus.NOT_FOUND)

            return expenseRepository.findById(ExpenseID)
                .orElseThrow { ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR) }
    }

    // Get all Expenses belonging to the same Category.
//    fun getExpenseByCategoryID(CategoryID: Int): List<Expense?>? {
//        try {
//            if (CategoryID <= 0)
//                error(HttpStatus.BAD_REQUEST)
//
//            return expenseRepository.getExpensesByCategoryID(CategoryID)
//        }
//
//        catch (e: Exception) {
//            error(HttpStatus.INTERNAL_SERVER_ERROR)
//        }
//    }

    // Create an Expense.
    fun createExpense(expense: Expense): Expense {
            if (expense.ExpenseID <= 0)
                throw ResponseStatusException(HttpStatus.BAD_REQUEST)

            if (expenseRepository.existsById(expense.ExpenseID))
                throw ResponseStatusException(HttpStatus.BAD_REQUEST)

            if (!categoryRepository.existsById(expense.categoryID))
                throw ResponseStatusException(HttpStatus.BAD_REQUEST)

            return expenseRepository.save(expense)
    }

    // Update an Expense.
    fun updateExpenseById(ExpenseID: Int, expense: Expense): Expense {
        if (ExpenseID <= 0)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)

        return if (expenseRepository.existsById(ExpenseID)) {
            expenseRepository.save(
                Expense(
                    ExpenseID = expense.ExpenseID,
                    expenseDate = expense.expenseDate,
                    Amount = expense.Amount,
                    categoryID = expense.categoryID
                )
            )
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    // Delete an Expense.
    fun deleteExpenseById(ExpenseID: Int) {
        if (ExpenseID <= 0)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)

        return if (expenseRepository.existsById(ExpenseID)) {
            expenseRepository.deleteById(ExpenseID)

        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }


}