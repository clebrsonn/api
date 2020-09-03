package br.com.hyteck.api.repository

import br.com.hyteck.api.record.Tecnology
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface TecnologyRepository : JpaRepository<Tecnology, String>, JpaSpecificationExecutor<Tecnology>, CustomTecnologyRepository {
}