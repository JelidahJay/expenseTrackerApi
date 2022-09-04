package com.expenseTracker.constants

object routeConstants {

    // Cross Origin Route for React JS frontend
    const val CORS = "http://localhost:3000"

    //
    const val ControllerMapping = "/api/v1/expenseTracker"

    // Route : Get all categories.
    const val categories = "/categories"

    // Route : Get category by ID.
    const val getCategoryById = "/categories/{CategoryID}"

    // Route : Get category by category name.
    const val getCategoryByName = "/category/categoryName/{categoryName}"

    // Route : Post - create a new category.
    const val createCategory = "/category/create"

    // Route : Put - update a category.
    const val updateCategory = "/categoryUpdate/{CategoryID}"

    // Route : Delete - delete a category.
    const val deleteCategory = "/categoryDelete/{CategoryID}"

    // Route : Get all categories.
    const val expenses = "/expenses"

    // Route : Get expense by ID.
    const val getExpenseById = "/expenses/{ExpenseID}"

    // Route : Get expense by expense name.
    const val getExpenseByName = "/expense/expenseName/{expenseName}"

    // Route : Post - create a new expense.
    const val createExpense = "/expense/create"

    // Route : Put - update an expense.
    const val updateExpense = "/expenseUpdate/{ExpenseID}"

    // Route : Delete - delete an expense.
    const val deleteExpense = "/expenseDelete/{ExpenseID}"
}