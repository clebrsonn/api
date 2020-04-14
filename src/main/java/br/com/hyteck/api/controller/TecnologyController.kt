package br.com.hyteck.api.controller

import br.com.hyteck.api.dto.SearchOptions
import br.com.hyteck.api.record.Tecnology
import br.com.hyteck.api.repository.TecnologyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tecnologies")
class TecnologyController {

    @Autowired
    lateinit var tecnologyRepository: TecnologyRepository

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @GetMapping
    fun list(): MutableIterable<Tecnology> {
            return tecnologyRepository.findAll()
        }

    @PostMapping(consumes = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody tecs: List<Tecnology>){
        tecnologyRepository.saveAll(tecs.filterNotNull())

    }

    @GetMapping("/search")
    fun search(@RequestBody searchOptipons: SearchOptions): MutableIterable<Tecnology> {
        return tecnologyRepository.findAll(SearchOptions.SearchSpecitication.filterWithOptions(searchOptipons))
    }
}