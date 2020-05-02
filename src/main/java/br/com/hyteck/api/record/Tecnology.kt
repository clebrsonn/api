package br.com.hyteck.api.record

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table
class Tecnology {

    @Id
    val nameTec: String = ""

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
//    @Formula("(select AVG(a.range_m) from Tecnology a)")
//    val avgRange_m: Double? = null
//
//
//    @Formula("(select AVG(a.tx_data) from Tecnology a)")
//    val avgTx: Double? = null
//
//    @Formula("(select AVG(a.bandwidth) from Tecnology a)")
//    val avgBandwidth: Double? = null
//
//    @Formula("(select AVG(a.consumption) from Tecnology a)")
//    val avgConsumption: Double? = null

    @OneToOne(mappedBy = "tecnology")
    lateinit var normalizedTecnology: NormalizedTecnology


}