package br.com.hyteck.api.repository

import br.com.hyteck.api.record.Technology
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManager

@Component
class TechnologyRepositoryImpl : CustomTechnologyRepository {

    @Autowired
    private lateinit var entityManager: EntityManager

    override fun findAllByCategories(ids: MutableSet<Long>) : MutableList<Technology>{
        var queryString = "Select tec from Technology as tec "

        ids.forEach { id->
            queryString= "$queryString join tec.categories as cat$id with cat$id.id = :cat$id"
        }

         val query = entityManager.createQuery(queryString, Technology::class.java)

        ids.forEach { id->
        query.setParameter("cat$id", id)
        }

        return query.resultList
    }
}

interface CustomTechnologyRepository {

    fun findAllByCategories(ids: MutableSet<Long>) : MutableList<Technology>

}
