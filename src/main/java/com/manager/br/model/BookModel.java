package com.manager.br.model;

import jakarta.persistence.*;
import lombok.*;

import java.awt.print.Book;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;
    private String bookTitle;
    private String bookAuthor;
    private int ISBN;
    private int pages;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryModel category;

    @OneToMany(mappedBy = "bookModel")
    private List<CheckOutModel> checkOut;
}
