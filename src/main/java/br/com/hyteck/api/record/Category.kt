package br.com.hyteck.api.record

import br.com.hyteck.api.enums.TypeCategory
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.vladmihalcea.hibernate.type.range.PostgreSQLRangeType
import com.vladmihalcea.hibernate.type.range.Range
import org.hibernate.annotations.TypeDef
import java.math.BigDecimal
import javax.persistence.*


@Entity
@Table(uniqueConstraints =  arrayOf( UniqueConstraint(columnNames= arrayOf("range", "type"), name="range_type_constraint")))

@TypeDef(
        typeClass = PostgreSQLRangeType::class,
                defaultForType = Range::class
)
class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Enumerated(EnumType.STRING)
    lateinit var type : TypeCategory


    @Column(columnDefinition = "numrange")
    lateinit var range: Range<BigDecimal>

    @ManyToMany(cascade = [
        CascadeType.PERSIST,
        CascadeType.MERGE
    ])
    @JoinTable(name = "category_tecnology",
            joinColumns = [JoinColumn(name = "category_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "tec_id", referencedColumnName = "id")]
    )
    @JsonManagedReference
    var technologies: MutableList<Technology> = mutableListOf()

    fun addTecnology(technology : Technology){
        technologies.add(technology)
        technology.categories.add(this)
    }

    fun removeTecnology(technology : Technology){
        technologies.remove(technology)
        technology.categories.remove(this)
    }

}