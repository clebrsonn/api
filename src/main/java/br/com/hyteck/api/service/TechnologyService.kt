package br.com.hyteck.api.service

import br.com.hyteck.api.dto.SearchOptions
import br.com.hyteck.api.record.NormalizedTechnology
import br.com.hyteck.api.record.Technology
import br.com.hyteck.api.repository.NormalizedTechnologyRepository
import br.com.hyteck.api.repository.TechnologyRepository
import br.com.hyteck.api.repository.specification.SearchSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.function.Consumer
import java.util.stream.Collectors

@Service
class TechnologyService {

    @Autowired
    private lateinit var technologyRepository: TechnologyRepository

    @Autowired
    private lateinit var categoryService: CategoryService

    @Autowired
    private  lateinit var normalizedTechnologyRepository: NormalizedTechnologyRepository

    fun calculate(searchOptions: SearchOptions): MutableList<Technology?>? {

        val where = SearchSpecification.normal(searchOptions)

        return normalizedTechnologyRepository.findAll(where).stream().map { t -> t.technology }.collect(Collectors.toUnmodifiableList())

    }

    fun calculateMedia(): MutableList<NormalizedTechnology> {

        val tecnologies = technologyRepository.findAll()

        tecnologies.forEach(Consumer { tecnology ->
            if(!normalizedTechnologyRepository.existsById(tecnology.nameTec)){
                val normalizedTecnology = NormalizedTechnology(tecnology)
                normalizedTechnologyRepository.save(normalizedTecnology)
            }else{
                val tecForUpdate = normalizedTechnologyRepository.findById(tecnology.nameTec).get()
                tecForUpdate.normalize(tecnology)
                normalizedTechnologyRepository.save(tecForUpdate)
            }
        })

        return normalizedTechnologyRepository.findAll()
    }

    fun findAll(): MutableList<Technology>{
        return technologyRepository.findAll()
    }

    fun findAllByCategories(catIds: MutableSet<Long>): MutableList<Technology> {
        return technologyRepository.findAllByCategories(catIds)
    }

    fun saveAll(filterNotNull: List<Technology>) : MutableList<Technology> {
        val tecnologies = technologyRepository.saveAll(filterNotNull)
        categoryService.calculateCategories(tecnologies)

        return tecnologies
    }

//    fun save(tec: Technology) : MutableList<Technology>{
//        val technology = tecnologyRepository.save(tec)
//        categoryService.calculateCategories(mutableListOf(technology))
//        return technology;
//    }

}