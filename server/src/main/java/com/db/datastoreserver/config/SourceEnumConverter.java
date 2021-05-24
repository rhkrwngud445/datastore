package com.db.datastoreserver.config;

import com.db.datastoreserver.domain.post.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class SourceEnumConverter implements Converter<String, Category> {
    @Override
    public Category convert(String value) {
        return Category.valueOf(value.toUpperCase());
    }
}

