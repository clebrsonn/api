package br.com.hyteck.api.repository.specification;

import br.com.hyteck.api.dto.SearchOptions;
import br.com.hyteck.api.record.NormalizedTechnology;
import org.springframework.data.jpa.domain.Specification;


public class SearchSpecification {

//    private static final String FIELD_SEPARATOR = ".";
//    private static final String REGEX_FIELD_SPLITTER = "\\.";

    public static Specification<NormalizedTechnology> normal(SearchOptions searchOptions) {
        return (Specification<NormalizedTechnology>) (root, criteriaQuery, criteriaBuilder) -> {

            //(consumption/sqrt((consumption^2)+(tx_data ^2)+(range_m ^2)))

            final var variance = Math.sqrt(searchOptions.options.values().stream()
                    .map(aDouble -> Math.pow(aDouble, 2)).mapToDouble(aDouble2 -> aDouble2).sum());


            final var sumVetor = searchOptions.options.values().stream().mapToDouble(value -> value / variance).sum();

            return criteriaBuilder.lessThanOrEqualTo(root.get("vetor"), sumVetor);

        };
    }


//    public static Specification<Technology> filterWithOptions(SearchOptions params) {
//        return (root, query, criteriaBuilder) -> {
//            try {
//                List<Predicate> predicates = new ArrayList<>();
//                for (String field : params.options.keySet()) {
//                    if (field.contains(FIELD_SEPARATOR)) {
//                        filterInDepth(params.options, root, criteriaBuilder, predicates, field);
//                    } else {
//                        if (Technology.class.getDeclaredField(field) != null) {
//                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(field), params.options.get(field)));
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


//    private static void filterInDepth(Map<String, Double> params, Root<Technology> root, CriteriaBuilder criteriaBuilder,
//                                      List<Predicate> predicates, String field) throws NoSuchFieldException {
//        String[] compositeField = field.split(REGEX_FIELD_SPLITTER);
//        if (compositeField.length == 2) {
//            if (Collection.class.isAssignableFrom(Technology.class.getDeclaredField(compositeField[0]).getType())) {
//                Join<Object, Object> join = root.join(compositeField[0]);
//                predicates.add(criteriaBuilder.greaterThanOrEqualTo(join.get(compositeField[1]), params.get(field)));
//            }
//        } else if (Technology.class.getDeclaredField(compositeField[0]).getType().getDeclaredField(compositeField[1]) != null) {
//            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(compositeField[0]).get(compositeField[1]), params.get(field)));
//        }
//    }

}
