package br.com.hyteck.api.record

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "tecnologies")
data class Tecnology(@Id
                     val name: String = "",
                     @Column(name="consumo_dbm")
                     val energyConsumption: String = "",
                     @Column(name="alcance")
                     val distance: String = "",
                     @Column(name="bateria")
                     val batery: String=""): Serializable