package br.com.hyteck.api.controller

import br.com.hyteck.api.enums.RangeType
import br.com.hyteck.api.enums.TypeCategory
import br.com.hyteck.api.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("categories")
class CategoryController {

    @Autowired
    lateinit var categoryService: CategoryService

    @GetMapping
    fun save(lower: BigDecimal, upper: BigDecimal, rangeType: RangeType, typeCategory: TypeCategory) {
        categoryService.save(lower, upper, rangeType, typeCategory)
    }
}