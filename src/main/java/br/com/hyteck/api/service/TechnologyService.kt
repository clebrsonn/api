package br.com.hyteck.api.service

import br.com.hyteck.api.dto.SearchOptions
import br.com.hyteck.api.record.NormalizedTechnology
import br.com.hyteck.api.record.StatisticalTechnologies
import br.com.hyteck.api.record.Technology
import br.com.hyteck.api.repository.NormalizedTechnologyRepository
import br.com.hyteck.api.repository.StatisticalTechnologiesRepository
import br.com.hyteck.api.repository.TechnologyRepository
import br.com.hyteck.api.repository.specification.SearchSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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

    @Autowired
    lateinit var statisticalTechnologiesRepository: StatisticalTechnologiesRepository

    open fun calculate(searchOptions: SearchOptions): MutableList<Technology?>? {

        val where = SearchSpecification.normal(searchOptions)

        return normalizedTechnologyRepository.findAll(where).stream().map { t -> t.technology }.collect(Collectors.toUnmodifiableList())

    }

    open fun calculateMedia(): MutableList<NormalizedTechnology> {

        val tecnologies = technologyRepository.findAll()

        tecnologies.forEach(Consumer { tecnology ->
            if(!normalizedTechnologyRepository.existsById(tecnology.id)){
                val normalizedTecnology = NormalizedTechnology(tecnology)
                normalizedTechnologyRepository.save(normalizedTecnology)
            }else{
                val tecForUpdate = normalizedTechnologyRepository.findById(tecnology.id).get()
                tecForUpdate.normalize(tecnology)
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
        categoryService.calculateCategories(tecnologies)

        return tecnologies
    }
    @Transactional
    open fun calcStatisticals() : MutableList<StatisticalTechnologies> {
        val tecs = technologyRepository.findAll()
        val techs = SearchSpecification.calcStatistics(tecs)
        techs.forEach(Consumer { tech -> statisticalTechnologiesRepository.save(tech.statisticalTechnologies!!)})

        return statisticalTechnologiesRepository.findAll()
    }

//    fun save(tec: Technology) : MutableList<Technology>{
//        val technology = tecnologyRepository.save(tec)
//        categoryService.calculateCategories(mutableListOf(technology))
//        return technology;
//    }

}