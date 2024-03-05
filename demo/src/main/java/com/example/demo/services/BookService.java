package com.example.demo.services;

import com.example.demo.models.BookModel;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;
    public List<BookModel> getAllBooks() {
        return repository.findAll();
    }

    public Optional<BookModel> getBookById(UUID id) {
        return repository.findById(id);
    }
    public BookModel createBook(BookModel book) {
        return repository.save(book);
    }

    public void deleteBook(UUID id) {
        repository.deleteById(id);
    }

    public BookModel update(BookModel book) {
        return repository.save(book);
    }
}
