package br.com.hyteck.api.repository

import br.com.hyteck.api.enums.TypeCategory
import br.com.hyteck.api.record.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CategoryRepository : JpaRepository<Category, Long>{

//    @Query(
//    "SELECT c from Category  c " +
//            "where ((lower_inf(range) OR (CASE WHEN lower_inc(range)" +
//            " THEN :range >= lower(range) ELSE :range > lower(range) END))     " +
//            "AND (upper_inf(range) OR (CASE WHEN upper_inc(range) THEN :range <= upper(range) " +
//            "ELSE :range < upper(range) END)))" //+
//            //" and type = :type"
//            , nativeQuery = true
//    )
   @Query("Select c.* from Category c where c.range @> cast(:range as numeric) and type = :type", nativeQuery = true)
    fun findByTypeAndRange(type: String, range: Number): Category
}