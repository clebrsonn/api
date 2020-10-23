package br.com.hyteck.api.dto;

import br.com.hyteck.api.enums.TypeCategory;
import br.com.hyteck.api.record.Category;
import com.vladmihalcea.hibernate.type.range.Range;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/***
 * Represents the range/interval with two bounds with the category type of the interval.
 * Abstraction follows the semantics of the mathematical interval. The range can be
 * unbounded or open from the left or/and unbounded from the right. The range supports half-open or closed
 * bounds on both sides.
 *
 */
@Data
public class CategoryDTO {

    private Long id;

    private String type;

    private String rangeType;

    public CategoryDTO(Category category) {
        this.type = category.getType().name();
        this.id = category.getId();
        this.rangeType = category.getRange().asString();

    }

    public static List<CategoryDTO> from(List<Category> categories) {
        return categories.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }


    public static Category to(CategoryDTO categoryDTO) {
        Category category = new Category();

        category.setType(TypeCategory.valueOf(categoryDTO.getType()));

        category.setRange(Range.bigDecimalRange(categoryDTO.getRangeType()));

        return category;
    }

}
