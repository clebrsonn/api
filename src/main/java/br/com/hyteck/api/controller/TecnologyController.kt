package br.com.hyteck.api.controller

import br.com.hyteck.api.dto.SearchOptions
import br.com.hyteck.api.record.NormalizedTecnology
import br.com.hyteck.api.record.Tecnology
import br.com.hyteck.api.service.TecnologyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tecnologies")
class TecnologyController {

    @Autowired
    lateinit var tecnologyService: TecnologyService

    @GetMapping
    fun list(): MutableIterable<Tecnology> {
        return tecnologyService.findAll()
    }

    @PostMapping(consumes = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody tecs: MutableList<Tecnology>): MutableList<Tecnology>{
        return tecnologyService.saveAll(tecs.filterNotNull())
    }


//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    fun save(@RequestBody tec: Tecnology) {
//        tecnologyService.save(tec)
//
//    }


    @GetMapping("/search")
    fun search(@RequestParam searchOptipons: SearchOptions): MutableList<Tecnology?>? {

        val lista = tecnologyService.calculate(searchOptipons)

        return lista
    }

    @GetMapping("/media")
    fun calculateMedia(): MutableList<NormalizedTecnology> {
        return tecnologyService.calculateMedia()
    }


    @GetMapping("/find-by-categories")
    fun findAllByCategories(@RequestParam catId: MutableSet<Long>): MutableList<Tecnology> {
        return tecnologyService.findAllByCategories(catId)
    }

}