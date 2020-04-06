package br.com.hyteck.api.controller

import br.com.hyteck.api.record.Tecnology
import br.com.hyteck.api.repository.TecnologyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("tecnologies")
class TecnologyController {

    @Autowired
    lateinit var tecnologyRepository: TecnologyRepository

    @GetMapping
    fun list(): MutableIterable<Tecnology> {
            return tecnologyRepository.findAll()
        }
}