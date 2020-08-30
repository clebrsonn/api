package br.com.hyteck.api.record

import com.vladmihalcea.hibernate.type.range.Range
import java.math.BigDecimal
import javax.persistence.*


@Entity
@Table
class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    lateinit var id: Number

    @Enumerated(EnumType.STRING)
    lateinit var type : TypeCategory

    @OneToMany
    @JoinColumn(name="category")
    lateinit var tecnology: MutableList<Tecnology>

    @Column(columnDefinition = "numrange")
    lateinit var valueRange: Range<BigDecimal>

}