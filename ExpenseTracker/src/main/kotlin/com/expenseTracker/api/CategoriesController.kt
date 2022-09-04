package com.expenseTracker.api

import com.expenseTracker.entities.Entity.Category
import com.expenseTracker.services.CategoryService
import com.expenseTracker.constants.routeConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


/**
 * Categories Controller to process incoming REST API requests, preparing the Category Entity and returning a response.
 */
@CrossOrigin(origins = [routeConstants.CORS])
@RestController
@RequestMapping(routeConstants.ControllerMapping)
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
    @GetMapping(routeConstants.categories)
    fun getAllCategories(): ResponseEntity<List<Category>> {
        return ResponseEntity.ok(categoryService.getAllCategories())
    }

    /**
     * Get Category by id.
     * URL: http://localhost:8080/api/expenseTracker/v1/categories/{CategoryID}
     */
    @GetMapping(routeConstants.getCategoryById)
    fun getCategoryById(@PathVariable("CategoryID") CategoryID: String): ResponseEntity<Category> {

        return ResponseEntity.ok(categoryService.getCategoryById(CategoryID))
    }

    /**
     * Get Category by category name.
     * URL: http://localhost:8080/api/expenseTracker/v1/categories/categoryName/{categoryName}
     */
    @GetMapping(routeConstants.getCategoryByName)
    fun getCategoryByName(@PathVariable("categoryName") categoryName: String): ResponseEntity<List<Category?>?> {
        return ResponseEntity.ok(categoryService.getCategoryByName(categoryName))
    }

    /**
     * Create a new Category.
     * URL: http://localhost:8080/api/expenseTracker/v1/categories
     */
    @PostMapping(routeConstants.createCategory)
    fun createCategory(@RequestBody @Valid payload: Category): Category {
        return categoryService.createCategory(payload)
    }

    /**
     * Update an existing Category.
     * URL: http://localhost:8080/api/expenseTracker/v1/categories/{CategoryID}
     */
    @PutMapping(routeConstants.updateCategory)
    fun updateCategoryById(@RequestParam("CategoryID") CategoryID: String, @RequestBody payload: Category): Category =
        categoryService.updateCategoryById(CategoryID, payload)

    /**
     * Delete a Category
     * URL: http://localhost:8080/api/expenseTracker/v1/categories/{CategoryID}
     */
    @DeleteMapping(routeConstants.deleteCategory)
    fun deleteCategoryById(@PathVariable("CategoryID") CategoryID: String): Unit =
        categoryService.deleteCategoryById(CategoryID)
}