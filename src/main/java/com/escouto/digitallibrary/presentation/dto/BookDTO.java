package com.escouto.digitallibrary.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class BookDTO {
    private Long id;
    private String title;
    private String author;
}
