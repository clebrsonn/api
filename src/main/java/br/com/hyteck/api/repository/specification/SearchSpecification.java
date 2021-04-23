package br.com.hyteck.api.repository.specification;

import br.com.hyteck.api.enums.TypeCategory;
import br.com.hyteck.api.record.Category;
import br.com.hyteck.api.record.Technology;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

public class SearchSpecification {

    public static Specification<Technology> where(Double range) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.ge(root.get(TypeCategory.RANGE.getType()), range);
    }

    public static Specification<Category> whereTechnologiesIn(List<Technology> technologies){
        return (root, criteriaQuery, criteriaBuilder) ->{
            var inClause = criteriaBuilder.in(root.join("technologies").get("id"));
            inClause.value(technologies.stream().map(Technology::getId).collect(Collectors.toList()));
            return inClause;
        };
    }
}
