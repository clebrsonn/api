package br.com.hyteck.api.record

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table
@Schema
class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Parameter
    @NotNull
    @NotBlank
//    @Min(2)
    lateinit var nameTec: String

    @Parameter
    var ieee: String? = null

    @Parameter
    var frequency: Double? = 0.0

    @Parameter
    var txData: Double? = null
        set(value) {
            field = value ?: 0.0
        }
        get() {
            if (field == null) {
                return 0.0
            }
            return field
        }

    @Parameter
    @Column(name="range_m")
    var rangeM: Double? = 0.0
        set(value) {
            field = value ?: 0.0
        }
        get() {
            if (field == null) {
                return 0.0
            }
            return field
        }

    @Parameter
    var bandwidth: Double? = 0.0
        set(value) {
            field = value ?: 0.0
        }
        get() {
            if (field == null) {
                return 0.0
            }
            return field
        }

    @Parameter
    var nrNodes: Int? = 0

    @Parameter
    var consumption: Double? = 0.0
        set(value) {
            field = value ?: 0.0
        }
        get() {
            if (field == null) {
                return 0.0
            }
            return field
        }

    @ManyToMany(mappedBy = "technologies", fetch = FetchType.LAZY)
    @JsonIgnore
    var categories: MutableSet<Category> = mutableSetOf()

}