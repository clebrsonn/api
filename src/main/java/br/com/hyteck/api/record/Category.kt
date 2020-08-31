package br.com.hyteck.api.record

import com.vladmihalcea.hibernate.type.range.PostgreSQLRangeType
import com.vladmihalcea.hibernate.type.range.Range
import org.hibernate.annotations.TypeDef
import java.math.BigDecimal
import javax.persistence.*


@Entity
@Table
@TypeDef(
        typeClass = PostgreSQLRangeType::class,
                defaultForType = Range::class
)
class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    var id: Long = 0

    @Enumerated(EnumType.STRING)
    lateinit var type : TypeCategory

    @ManyToMany(cascade = [
        CascadeType.PERSIST,
        CascadeType.MERGE
    ])
    @JoinTable(name = "category_tecnology",
            joinColumns = [JoinColumn(name = "category_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "tec_id", referencedColumnName = "nameTec")]
    )
    var tecnology: MutableList<Tecnology> = mutableListOf()

    @Column(columnDefinition = "numrange")
    lateinit var range: Range<BigDecimal>

}