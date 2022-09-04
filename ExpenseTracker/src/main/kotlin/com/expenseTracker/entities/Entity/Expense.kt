package com.expenseTracker.entities.Entity

import com.expenseTracker.utilities.BaseEntity
import java.util.Date
import javax.persistence.*

/**
 * Expense entity.
 */
@Entity
@Table(name = "expenses")
data class Expense (

    // Primary key of the expenses table.
    @Id
    @Column(name = "expense_id")
    var ExpenseID: Int,

    // Date of expenditure.
    @Column(name = "expense_date", nullable = true)
    var expenseDate: Date,

    // Expense amount.
    @Column(name = "expense_amount", nullable = false)
    var Amount : Int,

    // Foreign key, reference of Categories table.
    @Column(name = "category_id", nullable = false)
    var categoryID : String,

    // Navigation property.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", insertable = false, updatable = false, referencedColumnName = "category_id")
    private var category: Category? = null

) : BaseEntity()