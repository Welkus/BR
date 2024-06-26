package com.manager.br.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private String bookTitle;
    private String bookAuthor;
    private int ISBN;
    private int pages;
    private Long categoryId;
}