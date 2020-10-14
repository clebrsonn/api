package br.com.hyteck.api.dto

import br.com.hyteck.api.enums.TypeCategory
import lombok.Data
import java.io.Serializable


@Data
class SearchOptions: Serializable {

    lateinit var options: MutableMap<TypeCategory, Double>

}