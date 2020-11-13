package br.com.hyteck.api.dto;

import br.com.hyteck.api.enums.TypeCategory;
import br.com.hyteck.api.record.Category;
import com.vladmihalcea.hibernate.type.range.Range;
import lombok.Data;

import java.math.BigDecimal;
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
        this.rangeType = naturalize(category.getRange());

    }

    public static List<CategoryDTO> from(List<Category> categories) {
        return categories.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    private String naturalize(Range<BigDecimal> range){
        var naturalized = new StringBuilder();

        var lower = range.hasLowerBound()? range.lower().doubleValue() : 0;
        if(lower == 0){
            naturalized.append("menor ");
        }else if(range.asString().startsWith("[")){
            naturalized.append("de ");
        }else{
            naturalized.append("intervalo entre ");
        }
        naturalized.append(range.hasLowerBound()? range.lower().doubleValue(): "");


        if(range.asString().endsWith("]")){
            naturalized.append(" até ");

        }else{
            naturalized.append(" e ");

        }
        naturalized.append(range.hasUpperBound()? range.upper().doubleValue() :"");


        return naturalized.toString();


    }


    public static Category to(CategoryDTO categoryDTO) {
        Category category = new Category();

        category.setType(TypeCategory.valueOf(categoryDTO.getType()));

        category.setRange(Range.bigDecimalRange(categoryDTO.getRangeType()));

        return category;
    }

}
