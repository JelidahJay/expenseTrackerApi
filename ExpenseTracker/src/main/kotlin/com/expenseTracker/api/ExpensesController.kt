package com.expenseTracker.api

import com.expenseTracker.Entities.Entity.Expense
import com.expenseTracker.Services.ExpenseService
import com.expenseTracker.constants.RouteConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Expenses Controller to process incoming REST API requests, preparing the Category Entity and returning a response.
 */
@CrossOrigin(origins = [RouteConstants.CORS])
@RestController
@RequestMapping(RouteConstants.ControllerMapping)
class ExpensesController (

    // Expenses Service where all business functionalities will be fetched for the Expenses Controller.
    @Autowired
    private val expenseService: ExpenseService){

    /**
     * Get all Expenses.
     * URL: http://localhost:8080/api/expenseTracker/v1/expenses
     */
    @GetMapping(RouteConstants.expenses)
    fun getAllExpenses(): List<Expense> = expenseService.getAllExpenses()

    /**
     * Get Expense by id.
     * URL: http://localhost:8080/api/expenseTracker/v1/expenses/{ExpenseID}
     */
    @GetMapping(RouteConstants.getExpenseById)
    fun getExpenseById(@PathVariable("ExpenseID") ExpenseID: Int): Expense? {
        return expenseService.getExpenseById(ExpenseID)
    }

    /**
     * Get all Expenses belonging to the same Category.
     * URL: http://localhost:8080/api/expenseTracker/v1/expenses/{CategoryID}
     */
//    @GetMapping("/category/expenses/{CategoryID}")
//    fun getExpenseByCategoryID(@PathVariable("CategoryID") CategoryID: Int): List<Expense?>? {
//        return expenseService.getExpenseByCategoryID(CategoryID)
//    }

    /**
     * Create an Expense.
     * URL: http://localhost:8080/api/expenseTracker/v1/expenses
     */
    @PostMapping(RouteConstants.createExpense)
    fun createExpense(@RequestBody payload: Expense): Expense {
        return expenseService.createExpense(payload)
    }

    /**
     * Update an Expense.
     * URL: http://localhost:8080/api/expenseTracker/v1/expenses/{ExpenseID}
     */
    @PutMapping(RouteConstants.updateExpense)
    fun updateExpenseById(@PathVariable("ExpenseID") ExpenseID: Int, @RequestBody payload: Expense): Expense =
        expenseService.updateExpenseById(ExpenseID, payload)

    /**
     * Delete an Expense.
     * URL: http://localhost:8080/api/expenseTracker/v1/expenses/{ExpenseID}
     */
    @DeleteMapping(RouteConstants.deleteExpense)
    fun deleteExpenseById(@PathVariable("ExpenseID") ExpenseID: Int): Unit =
        expenseService.deleteExpenseById(ExpenseID)
}