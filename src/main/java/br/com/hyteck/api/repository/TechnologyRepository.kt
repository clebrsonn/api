package br.com.hyteck.api.repository

import br.com.hyteck.api.record.Technology
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface TechnologyRepository : JpaRepository<Technology, String>, JpaSpecificationExecutor<Technology>, CustomTechnologyRepository {
}