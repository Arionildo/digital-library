package com.escouto.digitallibrary.domain.specification;

import com.escouto.digitallibrary.domain.entity.Book;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public final class BookSpecification {

    private BookSpecification() {}

    public static Specification<Book> titleContains(String title) {
        if (StringUtils.isEmpty(title)) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Book> authorContains(String author) {
        if (StringUtils.isEmpty(author)) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" + author.toLowerCase() + "%");
    }
}