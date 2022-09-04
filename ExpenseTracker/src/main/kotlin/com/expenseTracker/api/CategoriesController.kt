package com.expenseTracker.api

import com.expenseTracker.Entities.Entity.Category
import com.expenseTracker.Services.CategoryService
import com.expenseTracker.constants.RouteConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


/**
 * Categories Controller to process incoming REST API requests, preparing the Category Entity and returning a response.
 */
@CrossOrigin(origins = [RouteConstants.CORS])
@RestController
@RequestMapping(RouteConstants.ControllerMapping)
class CategoriesController
    (
    // Category Service where all business functionalities will be fetched for the Categories Controller.
    @Autowired
    private val categoryService: CategoryService
) {

    /**
     * Get all Categories.
     * URL: http://localhost:8080/api/expenseTracker/v1/categories
     */
    @GetMapping(RouteConstants.categories)
    fun getAllCategories(): ResponseEntity<List<Category>> {
        return ResponseEntity.ok(categoryService.getAllCategories())
    }

    /**
     * Get Category by id.
     * URL: http://localhost:8080/api/expenseTracker/v1/categories/{CategoryID}
     */
    @GetMapping(RouteConstants.getCategoryById)
    fun getCategoryById(@PathVariable("CategoryID") CategoryID: String): ResponseEntity<Category> {

        return ResponseEntity.ok(categoryService.getCategoryById(CategoryID))
    }

    /**
     * Get Category by category name.
     * URL: http://localhost:8080/api/expenseTracker/v1/categories/categoryName/{categoryName}
     */
    @GetMapping(RouteConstants.getCategoryByName)
    fun getCategoryByName(@PathVariable("categoryName") categoryName: String): ResponseEntity<List<Category?>?> {
        return ResponseEntity.ok(categoryService.getCategoryByName(categoryName))
    }

    /**
     * Create a new Category.
     * URL: http://localhost:8080/api/expenseTracker/v1/categories
     */
    @PostMapping(RouteConstants.createCategory)
    fun createCategory(@RequestBody @Valid payload: Category): Category {
        return categoryService.createCategory(payload)
    }

    /**
     * Update an existing Category.
     * URL: http://localhost:8080/api/expenseTracker/v1/categories/{CategoryID}
     */
    @PutMapping(RouteConstants.updateCategory)
    fun updateCategoryById(@RequestParam("CategoryID") CategoryID: String, @RequestBody payload: Category): Category =
        categoryService.updateCategoryById(CategoryID, payload)

    /**
     * Delete a Category
     * URL: http://localhost:8080/api/expenseTracker/v1/categories/{CategoryID}
     */
    @DeleteMapping(RouteConstants.deleteCategory)
    fun deleteCategoryById(@PathVariable("CategoryID") CategoryID: String): Unit =
        categoryService.deleteCategoryById(CategoryID)
}