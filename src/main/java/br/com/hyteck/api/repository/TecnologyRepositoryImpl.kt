package br.com.hyteck.api.repository

import br.com.hyteck.api.record.Tecnology
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManager

@Component
class TecnologyRepositoryImpl : CustomTecnologyRepository {

    @Autowired
    private lateinit var entityManager: EntityManager

    override fun findAllByCategories(ids: MutableSet<Long>) : MutableList<Tecnology>{
        var queryString = "Select tec from Tecnology as tec "

        ids.forEach { id->
            queryString= "$queryString join tec.categories as cat$id with cat$id.id = :cat$id"
        }

         val query = entityManager.createQuery(queryString, Tecnology::class.java)

        ids.forEach { id->
        query.setParameter("cat$id", id)
        }

        return query.resultList
    }
}

interface CustomTecnologyRepository {

    fun findAllByCategories(ids: MutableSet<Long>) : MutableList<Tecnology>

}
