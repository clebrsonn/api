package br.com.hyteck.api.repository.specification;

import br.com.hyteck.api.enums.TypeCategory;
import br.com.hyteck.api.record.Technology;
import org.springframework.data.jpa.domain.Specification;

public class SearchSpecification {

    public static Specification<Technology> where(Double range) {

        return (Specification<Technology>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.between(root.get(TypeCategory.RANGE.getType()), range, (range * 1.3));

    }
}
