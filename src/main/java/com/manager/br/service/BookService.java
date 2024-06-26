package com.manager.br.service;

import com.manager.br.dto.BookDto;
import com.manager.br.model.BookModel;
import com.manager.br.model.CategoryModel;
import com.manager.br.repository.BookRepository;
import com.manager.br.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<BookModel> getBookById(long bookId) {
        return bookRepository.findById(bookId);
    }

    public void createBook(BookDto bookDto) {
        CategoryModel category = categoryRepository.findById(bookDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        BookModel book = BookModel.builder()
                .bookTitle(bookDto.getBookTitle())
                .bookAuthor(bookDto.getBookAuthor())
                .ISBN(bookDto.getISBN())
                .pages(bookDto.getPages())
                .category(category)
                .build();
        bookRepository.save(book);
    }

    public void editBook(long bookId, BookDto bookDto) {
        BookModel book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        CategoryModel category = categoryRepository.findById(bookDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        book.setBookTitle(bookDto.getBookTitle());
        book.setBookAuthor(bookDto.getBookAuthor());
        book.setISBN(bookDto.getISBN());
        book.setPages(bookDto.getPages());
        book.setCategory(category);
        bookRepository.save(book);
    }

    public void deleteBook(long bookId) {
        bookRepository.deleteById(bookId);
    }
}
