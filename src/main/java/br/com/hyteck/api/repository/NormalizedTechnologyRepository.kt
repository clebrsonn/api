package br.com.hyteck.api.repository

import br.com.hyteck.api.record.NormalizedTechnology
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface NormalizedTechnologyRepository : JpaRepository<NormalizedTechnology, String>, JpaSpecificationExecutor<NormalizedTechnology>{

}