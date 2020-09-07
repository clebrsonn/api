package br.com.hyteck.api.enums

import com.vladmihalcea.hibernate.type.range.Range
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

@Schema
enum class RangeType {
    CLOSED() {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> = Range.closed(lower, upper)
    },
    OPEN() {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> = Range.open(lower, upper)
    },
    INFINITE {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> = Range.infinite(BigDecimal::class.java)
    },
    OPEN_CLOSED {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> = Range.openClosed(lower, upper)
    },

    CLOSED_OPEN {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> = Range.closedOpen(lower, upper)
    },
    OPEN_INFINITE {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> = Range.openInfinite(lower)
    },
    CLOSED_INFINITE {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> = Range.closedInfinite(lower)
    },
    INFINITE_OPEN {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> = Range.infiniteOpen(upper)
    },
    INFINITE_CLOSED() {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> = Range.infiniteClosed(upper)
    };

    abstract fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal>
}