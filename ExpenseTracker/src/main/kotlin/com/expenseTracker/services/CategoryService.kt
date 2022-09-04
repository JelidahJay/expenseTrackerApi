package com.expenseTracker.services

import com.expenseTracker.entities.Entity.Category
import com.expenseTracker.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.lang.Integer.parseInt

/**
 * Category Service containing business functionalities for the Category Entity.
 */
@Service
class CategoryService
    (
    // Persistence layer for the Category Service.
    @Autowired
    private val categoryRepository : CategoryRepository
    ) {

    // Get all Categories in ascending order of the Category Name.
    fun getAllCategories(): List<Category> {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "categoryName"))
    }

    // Get category by id.
    fun getCategoryById(CategoryID: String): Category? {
            if (parseInt(CategoryID) <= 0)
                throw ResponseStatusException(HttpStatus.BAD_REQUEST)

            if (!categoryRepository.existsById(CategoryID))
                throw ResponseStatusException(HttpStatus.NOT_FOUND)

            return categoryRepository.findById(CategoryID)
                .orElseThrow { throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR) }
    }

    // Get Category by category name.
    fun getCategoryByName(categoryName : String): List<Category?>? {
            if (categoryName.isEmpty())
                throw ResponseStatusException(HttpStatus.BAD_REQUEST)
//            var category = await context.Categories //Needs to be redone properly
//                    .AsNoTracking()
//                .FirstOrDefaultAsync(c => c.CategoryName.ToLower() == categoryName.ToLower());

            val category = categoryRepository.findByCategoryName(categoryName.lowercase())

            if(category.isNullOrEmpty())
                throw ResponseStatusException(HttpStatus.NOT_FOUND)

            return category
    }

    // Create a new Category.
    fun createCategory(category: Category): Category {

            if (categoryRepository.existsByCategoryName(category.categoryName))
                throw ResponseStatusException(HttpStatus.BAD_REQUEST)

            if (categoryRepository.existsById(category.categoryID))
                throw ResponseStatusException(HttpStatus.BAD_REQUEST)

            val a = category.categoryID

            if (parseInt(a) <= 0)
                throw ResponseStatusException(HttpStatus.BAD_REQUEST)

            return categoryRepository.save(category)
    }

    // Update an existing Category.
    fun updateCategoryById(CategoryID: String, category: Category): Category {
            if (parseInt(CategoryID) <= 0)
                throw ResponseStatusException(HttpStatus.BAD_REQUEST)

            if (categoryRepository.existsByCategoryName(category.categoryName))
                throw ResponseStatusException(HttpStatus.BAD_REQUEST)

            return if (categoryRepository.existsById(CategoryID)) {
                categoryRepository.save(
                    Category(
                        categoryID = category.categoryID,
                        categoryName = category.categoryName,
                        expenses = category.expenses
                            )
                     )

            } else error(HttpStatus.NOT_FOUND)
        }

    // Delete a Category
    fun deleteCategoryById(CategoryID: String) {
        if (parseInt(CategoryID) <= 0)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)

        return if (categoryRepository.existsById(CategoryID)) {
            categoryRepository.deleteById(CategoryID)

        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}