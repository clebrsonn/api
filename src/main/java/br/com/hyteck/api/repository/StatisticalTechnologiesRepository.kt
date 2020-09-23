package br.com.hyteck.api.repository

import br.com.hyteck.api.record.StatisticalTechnologies
import org.springframework.data.jpa.repository.JpaRepository

interface StatisticalTechnologiesRepository : JpaRepository<StatisticalTechnologies, Long> {
}