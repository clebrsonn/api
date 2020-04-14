//package br.com.hyteck.api.dto
//
//import br.com.hyteck.api.record.Tecnology
//import lombok.Getter
//import lombok.Setter
//import org.springframework.data.jpa.domain.Specification
//import java.io.Serializable
//import java.util.*
//import javax.persistence.criteria.CriteriaBuilder
//import javax.persistence.criteria.Join
//import javax.persistence.criteria.Predicate
//import javax.persistence.criteria.Root
//
//
//@Getter
//@Setter
//class SearchOptions(val options: Map<String, String>): Serializable{
//
//
//    private val FIELD_SEPARATOR = "."
//    private val REGEX_FIELD_SPLITTER = "\\."
//
//
//    fun filterWithOptions(searchOptions: SearchOptions) : Specification<Tecnology>? {
//        return (root, query, criteriaBuilder) -> {
//            try {
//                val  predicates : List<Predicate>
//                for (option in searchOptions.options.keys) {
//                    if(option.contains(FIELD_SEPARATOR)){
//                        filterInDepth(params, root, criteriaBuilder, predicates, option);
//                    }else{
//                        if (Tecnology.class.getDeclaredField(option) != null) {
//                            predicates.add(criteriaBuilder.equal(root.get(field), params.get(field)));
//                        }
//                    }
//
//
//                }
//                for (String field : searchOptions.options.ke) {
//                    if (field.contains(FIELD_SEPARATOR)) {
//                        filterInDepth(params, root, criteriaBuilder, predicates, field);
//                    } else {
//                        if (Tecnology.class.getDeclaredField(field) != null) {
//                            predicates.add(criteriaBuilder.equal(root.get(field), params.get(field)));
//                        }
//                    }
//                }
//                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            }
//            return null;
//        };
//    }
//
//    @Throws(NoSuchFieldException::class)
//    private fun filterInDepth(params: Map<String, String>, root: Root<Tecnology>, criteriaBuilder: CriteriaBuilder,
//                              predicates: MutableList<Predicate>, field: String) {
//        val compositeField: Array<String> = field.split(REGEX_FIELD_SPLITTER).toTypedArray()
//        if (compositeField.size == 2) {
//            if (MutableCollection::class.java.isAssignableFrom(Tecnology::class.java.getDeclaredField(compositeField[0]).getType())) {
//                val join: Join<Any, Any> = root.join(compositeField[0])
//                predicates.add(criteriaBuilder.equal(join.get(compositeField[1]), params[field]))
//            }
//        } else if (Tecnology::class.java.getDeclaredField(compositeField[0]).getType().getDeclaredField(compositeField[1]) != null) {
//            predicates.add(criteriaBuilder.equal(root.get<Any>(compositeField[0]).get<Any>(compositeField[1]), params[field]))
//        }
//    }
//
//}