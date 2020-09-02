package br.com.hyteck.api.enums

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name="TypeCategory")
enum class TypeCategory {
    ENERGY(), RANGE(), TX_DATA();
}