package br.com.hyteck.api.service

import br.com.hyteck.api.record.Category
import br.com.hyteck.api.enums.RangeType
import br.com.hyteck.api.enums.TypeCategory
import br.com.hyteck.api.record.Tecnology
import br.com.hyteck.api.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
open class CategoryService {

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @Autowired
    lateinit var tecnologyService: TecnologyService

    @Transactional
    open fun save(lower: BigDecimal, upper: BigDecimal, rangeType: RangeType, typeCategory: TypeCategory) : MutableList<Category> {
        val category = Category()
        category.range = rangeType.apply(lower, upper)
        category.type = typeCategory
        categoryRepository.save(category)

        calculateCategories()
        return categoryRepository.findAll()
    }

    open fun calculateCategories(tecnologies : MutableList<Tecnology> = tecnologyService.findAll()){
        tecnologies.forEach  {
            val catRange = it.range_m?.let { range -> categoryRepository.findByTypeAndRange(type = TypeCategory.RANGE.name, range = range) }
            val catTxData = it.tx_data?.let { txData -> categoryRepository.findByTypeAndRange(type = TypeCategory.TX_DATA.name, range = txData) }

            catRange?.addTecnology(it)
            catTxData?.addTecnology(it)

            catRange?.let { categoryRepository.save(catRange) }
            catTxData?.let { categoryRepository.save(catTxData) }
        }
    }

    open fun findAll(): MutableList<Category> {
        return categoryRepository.findAll()
    }


}