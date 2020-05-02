package br.com.hyteck.api.dto

import lombok.Getter
import lombok.Setter
import java.io.Serializable


@Getter
@Setter
class SearchOptions: Serializable {

    lateinit var options: MutableMap<String, Double>

    fun zScore(){

        //z score = (x - media(x)) / desvio padrao (x)

    }
    fun minMax() {
     //   minMax = (X - min(x)) / max(x) - min(x)
    }



}