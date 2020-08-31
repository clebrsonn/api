package br.com.hyteck.api.repository

import br.com.hyteck.api.record.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, String>