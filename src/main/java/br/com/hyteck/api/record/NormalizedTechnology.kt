package br.com.hyteck.api.record

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import kotlin.math.pow
import kotlin.math.sqrt

@Entity
@Table
class NormalizedTechnology {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @MapsId
    @JsonIgnore
    lateinit var technology: Technology

    @Id
    val id: Long? = null

    var calcRangeM = 0.0

//    var calcBandwidth : Double? = null

    var calcConsumption = 0.0

    var calcTx = 0.0

    var vetor: Double? = null


    constructor()

    constructor(technology: Technology) {
        normalize(technology)
    }

    fun normalize(technology: Technology) {
        this.technology = technology

        val cal = sqrt(
                //(technology.consumption!!).pow(2.0)
                //.plus
                ((technology.txData!!).pow(2.0))
                .plus((technology.rangeM!!).pow(2.0)))

        calcRangeM = technology.rangeM!!.div(cal)
     //   calcConsumption = technology.consumption!!.div(cal)
        calcTx = technology.txData!!.div(cal)

        vetor = calcTx
                //.plus(calcConsumption)
                .plus(calcRangeM)

    }
}