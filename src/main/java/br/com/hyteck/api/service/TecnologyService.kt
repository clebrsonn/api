package br.com.hyteck.api.service

import br.com.hyteck.api.dto.SearchOptions
import br.com.hyteck.api.repository.specification.SearchSpecitication
import br.com.hyteck.api.record.NormalizedTecnology
import br.com.hyteck.api.record.Tecnology
import br.com.hyteck.api.repository.NormalizedTecnologyRepository
import br.com.hyteck.api.repository.TecnologyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.function.Consumer
import java.util.stream.Collectors

@Service
class TecnologyService {

    @Autowired
    private lateinit var tecnologyRepository: TecnologyRepository

    @Autowired
    private  lateinit var normalizedTecnologyRepository: NormalizedTecnologyRepository


    fun calculate(searchOptions: SearchOptions): MutableList<Tecnology?>? {


        val where = SearchSpecitication.normal(searchOptions)

        return normalizedTecnologyRepository.findAll(where).stream().map { t -> t.tecnology }.collect(Collectors.toUnmodifiableList())


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

    fun findAll(): MutableList<Tecnology>{
        return tecnologyRepository.findAll()
    }

}