package br.com.hyteck.api.controller

import br.com.hyteck.api.dto.SearchOptions
import br.com.hyteck.api.record.NormalizedTecnology
import br.com.hyteck.api.record.Tecnology
import br.com.hyteck.api.repository.NormalizedTecnologyRepository
import br.com.hyteck.api.repository.TecnologyRepository
import br.com.hyteck.api.service.TecnologyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tecnologies")
class TecnologyController {

    @Autowired
    lateinit var tecnologyRepository: TecnologyRepository

    @Autowired
    lateinit var tecnologyService: TecnologyService

    @Autowired
    lateinit var normalizedTecnology: NormalizedTecnologyRepository


    @GetMapping
    fun list(): MutableIterable<Tecnology> {
            return tecnologyRepository.findAll()
        }

    @PostMapping(consumes = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody tecs: List<Tecnology>){
        tecnologyRepository.saveAll(tecs.filterNotNull())

    }

    @PostMapping("/search")
    fun search(@RequestBody searchOptipons: SearchOptions): MutableIterable<Tecnology> {

        return tecnologyService.calculate(searchOptipons)
    }

    @GetMapping("/media")
    fun test(): MutableList<NormalizedTecnology> {
        return tecnologyService.calculateMedia()
    }


}