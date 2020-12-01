package br.com.hyteck.api.controller

import br.com.hyteck.api.dto.TechnologyDTO
import br.com.hyteck.api.record.Technology
import br.com.hyteck.api.service.TechnologyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
    fun save(@RequestBody @Valid  tecs: MutableList<Technology>): MutableIterable<Technology> {
        return technologyService.save(tecs.filterNotNull())
    }

    @GetMapping("/search")
    fun search(@RequestParam params :Map<String, String>): MutableList<TechnologyDTO>? {

        val range: String = params.getValue("range")

        val txData: String = params.getValue("tx_data")

        val energy: String = params.getValue("energy")

        return technologyService.searchTec(range.toDouble(), txData.toDouble(), energy.toInt())
    }

    @GetMapping("/find-by-categories")
    fun findAllByCategories(@RequestParam catIds: MutableSet<Long>): MutableList<Technology> {
        return technologyService.findAllByCategories(catIds)
    }

}