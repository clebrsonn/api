package br.com.hyteck.api.controller

import br.com.hyteck.api.dto.CategoryDTO
import br.com.hyteck.api.enums.RangeType
import br.com.hyteck.api.enums.TypeCategory
import br.com.hyteck.api.record.Category
import br.com.hyteck.api.service.CategoryService
import br.com.hyteck.api.service.TechnologyService
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("categories")
class CategoryController {

    @Autowired
    lateinit var categoryService: CategoryService

    @Autowired
    lateinit var technologyService: TechnologyService

    @PostMapping
    @Parameters(value = [Parameter(name = "lower",
            description = "lower",
            example = "2.0",
            required = false),
        Parameter(name = "upper",
                description = "upper",
                example = "2.0",
                required = false),
        Parameter(name = "rangeType",
                description = "rangeType",
                example = "Closed",
                required = true,
                schema = Schema(implementation = RangeType::class)),
        Parameter(name = "typeCategory",
                description = "typeCategory",
                example = "RANGE",
                required = true,
                schema = Schema(implementation = TypeCategory::class))
    ])
    fun save(lower: BigDecimal, upper: BigDecimal, rangeType: RangeType, typeCategory: TypeCategory): MutableList<Category> {
        return categoryService.save(lower, upper, rangeType, typeCategory)
    }

    @GetMapping("/adjust")
    fun categories() {
        categoryService.calculateCategories(technologyService.findAll())
    }

    @GetMapping
    fun findAll(@RequestParam(required = false) a: String): MutableList<CategoryDTO> {
        return CategoryDTO.from(categoryService.findAll())
    }
}