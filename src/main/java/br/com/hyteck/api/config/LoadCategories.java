package br.com.hyteck.api.config;

import br.com.hyteck.api.service.CategoryService;
import br.com.hyteck.api.service.TechnologyService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LoadCategories {

    private final CategoryService categoryService;

    private final TechnologyService technologyService;

    public LoadCategories(CategoryService categoryService, TechnologyService technologyService) {
        this.categoryService = categoryService;
        this.technologyService = technologyService;
    }

    @EventListener({ApplicationReadyEvent.class, ContextRefreshedEvent.class})
    @Transactional
    public void loadCategories(){
        categoryService.calculateCategories(technologyService.findAll());
    }

}
