package br.com.hyteck.api.repository

import br.com.hyteck.api.dto.SearchOptions
import br.com.hyteck.api.record.Technology
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManager

@Component
class TechnologyRepositoryImpl : CustomTechnologyRepository {

    @Autowired
    private lateinit var entityManager: EntityManager

    override fun findAllByCategories(ids: MutableSet<Long>): MutableList<Technology> {
        var queryString = "Select tec from Technology as tec "

        ids.forEach { id ->
            queryString = "$queryString join tec.categories as cat$id with cat$id.id = :cat$id"
        }

        val query = entityManager.createQuery(queryString, Technology::class.java)

        ids.forEach { id ->
            query.setParameter("cat$id", id)
        }

        return query.resultList
    }

    override fun findAllBySearchOptions(searchOptions: SearchOptions): MutableList<Technology> {
        var queryString = "Select tec from Technology as tec "

        searchOptions.options.forEach { (option, _) ->

            queryString = "$queryString inner join Technology as tec${option.ordinal} with tec.id = tec${option.ordinal}.id"

        }

        val query = entityManager.createQuery(queryString, Technology::class.java)

        searchOptions.options.forEach { (option, value) ->
            query.setParameter("tec${option.ordinal}", value)
        }
        return query.resultList
    }

}


interface CustomTechnologyRepository {

    fun findAllByCategories(ids: MutableSet<Long>): MutableList<Technology>
    fun findAllBySearchOptions(searchOptions: SearchOptions): MutableList<Technology>
}
