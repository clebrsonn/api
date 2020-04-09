package br.com.hyteck.api.controller

import br.com.hyteck.api.record.Tecnology
import br.com.hyteck.api.repository.TecnologyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tecnologies")
class TecnologyController {

    @Autowired
    lateinit var tecnologyRepository: TecnologyRepository

    @GetMapping
    fun list(): MutableIterable<Tecnology> {
            return tecnologyRepository.findAll()
        }

    @PostMapping(consumes = ["application/json"])
    fun save(@RequestBody tecs: List<Tecnology>): ResponseEntity<MutableIterable<Tecnology>> {
        val saveAll = tecnologyRepository.saveAll(tecs.filterNotNull())
        return  ResponseEntity.ok(saveAll)
    }
}