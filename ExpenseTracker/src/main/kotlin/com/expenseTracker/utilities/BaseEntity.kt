package com.expenseTracker.utilities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass

/**
 * Base properties of each class including datetime and date modified.
 */
@MappedSuperclass
abstract class BaseEntity
    (
     //Creation date of the row.
    @Column(name = "created_at" ,updatable = false)
    @CreationTimestamp
    private val createdAt: LocalDateTime? = null,

    // Last modification of the date of the row.
    @Column(name = "updated_at")
    @UpdateTimestamp
    private val updatedAt: LocalDateTime? = null
    )