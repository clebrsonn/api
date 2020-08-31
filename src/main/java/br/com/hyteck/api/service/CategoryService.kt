package br.com.hyteck.api.service

import br.com.hyteck.api.record.Category
import br.com.hyteck.api.record.RangeType
import br.com.hyteck.api.record.TypeCategory
import br.com.hyteck.api.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CategoryService {

    @Autowired
    lateinit var categoryRepository: CategoryRepository;

    fun save(lower: BigDecimal, upper: BigDecimal, rangeType: RangeType, typeCategory: TypeCategory) {
        val category = Category()
        category.range = rangeType.apply(lower, upper)
        category.type = typeCategory


        categoryRepository.save(category);


    }

}