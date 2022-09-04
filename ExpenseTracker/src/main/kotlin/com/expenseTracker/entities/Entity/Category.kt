package com.expenseTracker.entities.Entity

import com.expenseTracker.utilities.BaseEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import org.jetbrains.annotations.NotNull
import javax.persistence.*
import javax.validation.constraints.Max

/**
 * Category entity.
 */
@Entity
@Table(name = "categories")
data class Category(

    // Primary key for the table Categories table.
    @Id
    @Column(name = "category_id", unique = true, nullable = false)
    var categoryID: String,

    // Name of the category for each expense.
    @Column(name = "category_name", unique = true, nullable = false)
    @NotNull("This field is required")
    @Max(255, message = "Maximum number of words reached for this field")
    var categoryName: String,

    // Relationship between each Category and the Expenses attached to it.
    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.ALL))
    var expenses : List<Expense>? = null
) : BaseEntity()