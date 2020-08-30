package br.com.hyteck.api.repository

import br.com.hyteck.api.record.Tecnology
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Tecnology, String>