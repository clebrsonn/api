package br.com.hyteck.api.repository.specification;

import br.com.hyteck.api.enums.TypeCategory;
import br.com.hyteck.api.record.Technology;
import org.springframework.data.jpa.domain.Specification;

public class SearchSpecification {

    public static Specification<Technology> where(Double range) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.ge(root.get(TypeCategory.RANGE.getType()), range);
    }
}
