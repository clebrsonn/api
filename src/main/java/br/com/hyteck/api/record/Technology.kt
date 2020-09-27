package br.com.hyteck.api.record

import com.fasterxml.jackson.annotation.JsonBackReference
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import javax.persistence.*

@Entity
@Table
@Schema
class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Parameter
    lateinit var nameTec: String

    @Parameter
    var ieee: String? = null

    @Parameter
    var frequency: Double? = 0.0

    @Parameter
    var tx_data: Double? = null
        set(value) {
            field = value ?: 0.0
        }
        get() {
            if (field == null) {
                return 0.0
            }
            return field
        }

    @Parameter
    var range_m: Double? = 0.0
        set(value) {
            field = value ?: 0.0
        }
        get() {
            if (field == null) {
                return 0.0
            }
            return field
        }

    @Parameter
    var bandwidth: Double? = 0.0
        set(value) {
            field = value ?: 0.0
        }
        get() {
            if (field == null) {
                return 0.0
            }
            return field
        }

    @Parameter
    var nr_nodes: Int? = 0

    @Parameter
    var consumption: Double? = 0.0
        set(value) {
            field = value ?: 0.0
        }
        get() {
            if (field == null) {
                return 0.0
            }
            return field
        }

    @Parameter
    var classification: String? = null

    @ManyToMany(mappedBy = "technologies", fetch = FetchType.LAZY)
    @JsonBackReference
    var categories: MutableSet<Category> = mutableSetOf()

}