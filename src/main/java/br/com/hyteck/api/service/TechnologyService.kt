package br.com.hyteck.api.service

import br.com.hyteck.api.dto.TechnologyDTO
import br.com.hyteck.api.enums.TypeCategory
import br.com.hyteck.api.record.Technology
import br.com.hyteck.api.repository.TechnologyRepository
import br.com.hyteck.api.repository.specification.SearchSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
open class TechnologyService {

    @Autowired
    private lateinit var technologyRepository: TechnologyRepository

    open fun findAll(): MutableList<Technology> {
        return technologyRepository.findAll()
    }

    open fun findAllByCategories(catIds: MutableSet<Long>): MutableList<Technology> {
        return technologyRepository.findAllByCategories(catIds)
    }

    /**
     *
     */
    open fun save(technologies: List<Technology>): MutableList<Technology> {
        return technologyRepository.saveAll(technologies)

    }

    /**
     *
     * @param range shortest distance between the gateway and the node
     * @param tx_data value of the data transfer rate you need
     * @return a list of technologies that meet the requested requirements
     *         ordered by the values closest to those received as input
     *
     */
    open fun searchTec(range: Double, tx_data: Double): MutableList<TechnologyDTO> {
        val sort = Sort.by(Sort.Direction.ASC, TypeCategory.RANGE.type, TypeCategory.ENERGY.type)

        val technologies = technologyRepository.findAll(SearchSpecification.where(range), sort).stream()
                .filter { t -> t.txData!! >= tx_data }
                .sorted(Comparator.comparingDouble { t -> t.txData!! })
                .collect(Collectors.toList())
        var classified = ""

        if (technologies.size > 1) {
            val classifier = Classifier(technologies, 1)
            val prediction = classifier.knn.predict(doubleArrayOf(tx_data, range))
            classified = classifier.nominalScale.level(prediction)

        }
        return technologies.stream().map { tec -> TechnologyDTO(tec, classified) }.collect(Collectors.toList())

    }


}