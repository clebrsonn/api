package br.com.hyteck.api.repository

import br.com.hyteck.api.record.Technology
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface TechnologyRepository : JpaRepository<Technology, Long>, JpaSpecificationExecutor<Technology>, CustomTechnologyRepository