package br.com.hyteck.api.repository

import br.com.hyteck.api.record.Tecnology
import org.springframework.data.repository.CrudRepository

interface TecnologyRepository : CrudRepository<Tecnology, String> {
}