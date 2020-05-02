package br.com.hyteck.api.record

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table
class NormalizedTecnology {
    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    @JsonIgnore
    var tecnology: Tecnology? = null

    @Id
    val id: String? = null

    var calcRangeM = 0.0

//    var calcBandwidth : Double? = null

    var calcConsumption = 0.0

    var calcTx = 0.0

    var vetor: Double? = null


    constructor() {}
    constructor(tecnology: Tecnology) {
        normalize(tecnology)
    }

    fun normalize(tecnology: Tecnology) {
        this.tecnology = tecnology

        val cal = Math.sqrt(Math.pow(tecnology.consumption!!, 2.0)
                .plus(Math.pow(tecnology.tx_data!!, 2.0))
                .plus(Math.pow(tecnology.range_m!!, 2.0)))

        calcRangeM = tecnology.range_m!!.div(cal)
        calcConsumption = tecnology.consumption!!.div(cal)
        calcTx = tecnology.tx_data!!.div(cal)

        vetor = calcTx.plus(calcConsumption).plus(calcRangeM)

    }
}