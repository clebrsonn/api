package br.com.hyteck.api.controller

import br.com.hyteck.api.dto.SearchOptions
import br.com.hyteck.api.record.NormalizedTechnology
import br.com.hyteck.api.record.Technology
import br.com.hyteck.api.service.TechnologyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("technologies")
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

    @GetMapping("/search")
    fun search(@RequestParam range: Double, @RequestParam tx_data: Double): MutableList<Technology?>? {

        val lista = technologyService.searchTec(range, tx_data)

        return lista
    }


    @GetMapping("/calculate")
    fun calculate(@RequestParam searchOptipons: SearchOptions): MutableList<Technology?>? {

        val lista = technologyService.calculate(searchOptipons)

        return lista
    }

    @GetMapping("/media")
    fun calculateMedia(): MutableList<NormalizedTechnology> {
        return technologyService.calculateMedia()
    }


    @GetMapping("/find-by-categories")
    fun findAllByCategories(@RequestParam catIds: MutableSet<Long>): MutableList<Technology> {
        return technologyService.findAllByCategories(catIds)
    }

}