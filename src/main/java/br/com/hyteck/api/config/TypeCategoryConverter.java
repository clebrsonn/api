package br.com.hyteck.api.config;

import br.com.hyteck.api.enums.TypeCategory;
import br.com.hyteck.api.exception.TypeCategoryException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeCategoryConverter implements Converter<String, TypeCategory> {

    private final MessageSource messageSource;

    public TypeCategoryConverter(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public TypeCategory convert(String value) {
        try {
            return TypeCategory.valueOf(value);
        } catch (Exception ex) {
            throw new TypeCategoryException(messageSource.getMessage("enum.notfound", new String[]{value}, LocaleContextHolder.getLocale()), ex);
        }
    }
}
