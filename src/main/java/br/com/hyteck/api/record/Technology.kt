package br.com.hyteck.api.record

import com.fasterxml.jackson.annotation.JsonBackReference
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import javax.persistence.*
import javax.validation.Constraint

@Entity
@Table
@Schema
class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Parameter
    @Column(unique = true, nullable=false)
    lateinit var nameTec: String

    @Parameter
    val ieee: String? = null
    @Parameter
    val frequency: Double? = 0.0
    @Parameter
    var tx_data: Double? = null
        set(value) {
            if (value == null) {
                field = 0.0
            }
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
            if (value == null) {
                field = 0.0
            }
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
            if (value == null) {
                field = 0.0
            }
        }
        get() {
            if (field == null) {
                return 0.0
            }
            return field
        }

    @Parameter
    val nr_nodes: Int? = 0

    @Parameter
    var consumption: Double? = 0.0
        set(value) {
            if (value == null) {
                field = 0.0
            }
        }
        get() {
            if (field == null) {
                return 0.0
            }
            return field
        }

    @ManyToMany(mappedBy = "technologies", fetch = FetchType.EAGER)
    @JsonBackReference
    lateinit var categories: MutableSet<Category>


//    @PrePersist
//    fun cat(){
//
//        this.consumption
//
//        this.range_m
//
//        this.tx_data
//
//
//    }
}