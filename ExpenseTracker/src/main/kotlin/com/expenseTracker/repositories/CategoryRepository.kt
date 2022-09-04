package com.expenseTracker.repositories

import com.expenseTracker.entities.Entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Category Repository for storage, retrieval update, delete and search operations on the Category Entity.
 */
@Repository
interface CategoryRepository : JpaRepository<Category, String> {

    // Find a Category by its name.
    fun findByCategoryName(categoryName: String?): List<Category?>?

    // Check if Category exists by name.
    fun existsByCategoryName(categoryName: String): Boolean
}