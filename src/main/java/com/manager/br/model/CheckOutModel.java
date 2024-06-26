package com.manager.br.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class CheckOutModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isCheckedOut;

    private LocalDateTime checkOutDate;
    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookModel bookModel;


}
