package br.com.hyteck.api.config;

import br.com.hyteck.api.enums.TypeCategory;
import br.com.hyteck.api.exception.TypeCategoryException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeCategoryConverter implements Converter<String, TypeCategory> {
    @Override
    public TypeCategory convert(String value) {
        try {
            return TypeCategory.valueOf(value);
        } catch (Exception ex) {
            throw new TypeCategoryException("enum.notfound", ex);
        }
    }
}
