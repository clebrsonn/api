package br.com.hyteck.api.enums

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name="TypeCategory")
enum class TypeCategory(val type: String) {
    ENERGY("consumption"), RANGE("rangeM"), TX_DATA("txData");

}