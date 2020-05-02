package br.com.hyteck.api.repository

import br.com.hyteck.api.record.NormalizedTecnology
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface NormalizedTecnologyRepository : JpaRepository<NormalizedTecnology, String>, JpaSpecificationExecutor<NormalizedTecnology>{

}