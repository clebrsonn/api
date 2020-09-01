package br.com.hyteck.api.enums

import com.vladmihalcea.hibernate.type.range.Range
import java.math.BigDecimal

enum class RangeType {
    CLOSED() {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> {
            return Range.closed(lower, upper);

        }
    },
    OPEN() {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> = Range.open(lower, upper);


    },
    INFINITE {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> {
            return Range.infinite(BigDecimal::class.java);

        }
    },
    OPEN_CLOSED {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> {
            return Range.openClosed(lower, upper);

        }
    },

    CLOSED_OPEN {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> {
            return Range.closedOpen(lower, upper);

        }
    },
    OPEN_INFINITE {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> {
            return Range.openInfinite(lower);

        }
    },
    CLOSED_INFINITE {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> {
            return Range.closedInfinite(lower);

        }
    },
    INFINITE_OPEN {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> {
            return Range.infiniteOpen(upper);

        }
    },
    INFINITE_CLOSED() {
        override fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal> {
            return Range.infiniteClosed(upper);

        }
    };


    abstract fun apply(lower: BigDecimal, upper: BigDecimal): Range<BigDecimal>

}