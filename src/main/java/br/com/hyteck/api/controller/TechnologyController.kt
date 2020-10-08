package br.com.hyteck.api.controller

import br.com.hyteck.api.dto.SearchOptions
import br.com.hyteck.api.record.NormalizedTechnology
import br.com.hyteck.api.record.Technology
import br.com.hyteck.api.service.TechnologyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tecnologies")
class TechnologyController {

    @Autowired
    lateinit var technologyService: TechnologyService

    @GetMapping
    fun list(): MutableIterable<Technology> {
        return technologyService.findAll()
    }

    @PostMapping(consumes = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody tecs: MutableList<Technology>): MutableList<Technology>{
        return technologyService.saveAll(tecs.filterNotNull())
    }

    @PostMapping("/search-by-tec")
    fun s(@RequestBody searchOptipons: SearchOptions): MutableList<Technology?>? {

        val lista = technologyService.searchTec(searchOptipons)

        return lista
    }


    @GetMapping("/search")
    fun search(@RequestParam searchOptipons: SearchOptions): MutableList<Technology?>? {

        val lista = technologyService.calculate(searchOptipons)

        return lista
    }

    @GetMapping("/media")
    fun calculateMedia(): MutableList<NormalizedTechnology> {
        return technologyService.calculateMedia()
    }


    @GetMapping("/find-by-categories")
    fun findAllByCategories(@RequestParam catId: MutableSet<Long>): MutableList<Technology> {
        return technologyService.findAllByCategories(catId)
    }

}