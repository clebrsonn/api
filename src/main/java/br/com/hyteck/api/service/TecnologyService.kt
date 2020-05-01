package br.com.hyteck.api.service

import br.com.hyteck.api.dto.SearchOptions
import br.com.hyteck.api.record.NormalizedTecnology
import br.com.hyteck.api.record.Tecnology
import br.com.hyteck.api.repository.NormalizedTecnologyRepository
import br.com.hyteck.api.repository.TecnologyRepository
import org.hibernate.exception.ConstraintViolationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.function.Consumer

@Service
class TecnologyService {

    @Autowired
    lateinit var tecnologyRepository: TecnologyRepository

    @Autowired
    lateinit var normalizedTecnologyRepository: NormalizedTecnologyRepository


    fun calculate(searchOptions: SearchOptions): MutableIterable<Tecnology> {


        val where = SearchOptions.SearchSpecitication.filterWithOptions(searchOptions)

        return tecnologyRepository.findAll(where)


    }

    fun calculateMedia(): MutableList<NormalizedTecnology> {

        val tecnologies = tecnologyRepository.findAll()

        tecnologies.forEach(Consumer { tecnology ->
            if(!normalizedTecnologyRepository.existsById(tecnology.nameTec)){
                val normalizedTecnology = NormalizedTecnology(tecnology)
                normalizedTecnologyRepository.save(normalizedTecnology)
            }else{
                val tecForUpdate = normalizedTecnologyRepository.findById(tecnology.nameTec).get()
                tecForUpdate.normalize(tecnology)
                normalizedTecnologyRepository.save(tecForUpdate)
            }
        })

        return normalizedTecnologyRepository.findAll()


    }

}