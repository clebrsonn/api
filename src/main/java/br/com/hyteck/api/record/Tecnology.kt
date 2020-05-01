package br.com.hyteck.api.record

import org.hibernate.annotations.Formula
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

    val frequency: Double? = null

    val tx_data: Double? = null

    val range_m: Double? = null

    val bandwidth: Double? = null

    val nr_nodes: Int? = null

    val consumption: Double? = null

    @Formula("(select AVG(a.range_m) from Tecnology a)")
    val avgRange_m: Double? = null


    @Formula("(select AVG(a.tx_data) from Tecnology a)")
    val avgTx: Double? = null

    @Formula("(select AVG(a.bandwidth) from Tecnology a)")
    val avgBandwidth: Double? = null

    @Formula("(select AVG(a.consumption) from Tecnology a)")
    val avgConsumption: Double? = null

    @OneToOne(mappedBy = "tecnology")
    lateinit var normalizedTecnology : NormalizedTecnology


}