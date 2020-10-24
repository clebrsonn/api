package br.com.hyteck.api.controller

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
    fun save(@RequestBody tecs: MutableList<Technology>): MutableIterable<Technology> {
        return technologyService.save(tecs.filterNotNull())
    }

    @GetMapping("/search")
    fun search(@RequestParam params :Map<String, Double>): MutableList<Technology?>? {

        val range: Double = params.getValue("range")

        val txData: Double = params.getValue("tx_data")

        return technologyService.searchTec(range, txData)
    }

    @GetMapping("/find-by-categories")
    fun findAllByCategories(@RequestParam catIds: MutableSet<Long>): MutableList<Technology> {
        return technologyService.findAllByCategories(catIds)
    }

}