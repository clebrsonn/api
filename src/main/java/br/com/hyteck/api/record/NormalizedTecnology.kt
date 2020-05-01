package br.com.hyteck.api.record

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Getter
import lombok.Setter
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

    var calcBandwidth = 0.0

    var calcConsumption = 0.0

    var calcTx = 0.0


    constructor() {}
    constructor(tecnology: Tecnology) {
        normalize(tecnology)
    }

    fun normalize(tecnology: Tecnology) {
        this.tecnology = tecnology
        calcRangeM = tecnology.range_m?.div(tecnology.avgRange_m!!) ?: 0.0
        calcBandwidth = tecnology.bandwidth?.div(tecnology.avgBandwidth!!) ?: 0.0
        calcConsumption = tecnology.consumption?.div(tecnology.avgBandwidth!!) ?: 0.0
        calcTx = tecnology.tx_data?.div(tecnology.avgTx!!) ?: 0.0
    }
}