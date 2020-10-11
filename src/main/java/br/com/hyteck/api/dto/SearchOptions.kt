package br.com.hyteck.api.dto

import br.com.hyteck.api.enums.TypeCategory
import lombok.Getter
import lombok.Setter
import java.io.Serializable


@Getter
@Setter
class SearchOptions: Serializable {

    lateinit var options: MutableMap<TypeCategory, Double>

}