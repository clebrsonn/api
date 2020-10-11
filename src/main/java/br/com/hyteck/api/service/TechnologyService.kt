package br.com.hyteck.api.service

import br.com.hyteck.api.dto.SearchOptions
import br.com.hyteck.api.enums.TypeCategory
import br.com.hyteck.api.record.NormalizedTechnology
import br.com.hyteck.api.record.Technology
import br.com.hyteck.api.repository.NormalizedTechnologyRepository
import br.com.hyteck.api.repository.TechnologyRepository
import br.com.hyteck.api.repository.specification.SearchSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors

@Service
open class TechnologyService {

    @Autowired
    private lateinit var technologyRepository: TechnologyRepository

    @Autowired
    lateinit var categoryService: CategoryService

    @Autowired
    lateinit var normalizedTechnologyRepository: NormalizedTechnologyRepository

    open fun calculate(searchOptions: SearchOptions): MutableList<Technology?>? {

        val where = SearchSpecification.normal(searchOptions)

        return normalizedTechnologyRepository.findAll(where).stream().map { t -> t.technology }.collect(Collectors.toUnmodifiableList())

    }

    open fun calculateMedia(): MutableList<NormalizedTechnology> {

        val tecnologies = technologyRepository.findAll()

        tecnologies.forEach(Consumer { technology ->
            if (!normalizedTechnologyRepository.existsById(technology.id)) {
                val normalizedTecnology = NormalizedTechnology(technology)
                normalizedTechnologyRepository.save(normalizedTecnology)
            } else {
                val tecForUpdate = normalizedTechnologyRepository.findById(technology.id).get()
                tecForUpdate.normalize(technology)
                normalizedTechnologyRepository.save(tecForUpdate)
            }
        })

        return normalizedTechnologyRepository.findAll()
    }

    open  fun findAll(): MutableList<Technology>{
        return technologyRepository.findAll()
    }

    open fun findAllByCategories(catIds: MutableSet<Long>): MutableList<Technology> {
        return technologyRepository.findAllByCategories(catIds)
    }

    open fun saveAll(filterNotNull: List<Technology>) : MutableList<Technology> {
        val tecnologies = technologyRepository.saveAll(filterNotNull)
      //  categoryService.calculateCategories(tecnologies)

        return tecnologies
    }

    open fun searchTec(searchOptions: SearchOptions): MutableList<Technology?>? {
        val sort= Sort.by(Sort.Direction.ASC, TypeCategory.RANGE.type, TypeCategory.ENERGY.type)
        var list = technologyRepository.findAll(SearchSpecification.where(searchOptions), sort )
        list = list.stream()
                .filter { t -> t.txData!! >= searchOptions.options[TypeCategory.TX_DATA]!! }
                .sorted(Comparator.comparingDouble { t -> t.txData!! })
                .collect(Collectors.toList())

        return list
    }


//    fun save(tec: Technology) : MutableList<Technology>{
//        val technology = tecnologyRepository.save(tec)
//        categoryService.calculateCategories(mutableListOf(technology))
//        return technology;
//    }

}