package br.com.hyteck.api.record

import javax.persistence.*

@Entity
@Table
class Tecnology {

    @Id
    lateinit var nameTec: String

    val ieee: String? = null

    val frequency: Double? = 0.0

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

    val nr_nodes: Int? = 0

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
}