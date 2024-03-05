package com.example.demo.controllers;

import com.example.demo.dtos.BookDto;
import com.example.demo.models.BookModel;
import com.example.demo.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BookController {

    @Autowired
    BookService service;

    @GetMapping("/book")
    public List<BookModel> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/book/{id}")
    public Optional<BookModel> getBookById(@PathVariable UUID id) {
        return service.getBookById(id);
    }

    @PostMapping("/book")
    public BookModel createBook(@Valid @RequestBody BookDto bookDto) {
        BookModel bookModel = new BookModel();
        bookModel.setName(bookDto.getName());
        bookModel.setDescription(bookDto.getDescription());

        return service.createBook(bookModel);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable UUID id) {
        Optional<BookModel> book = service.getBookById(id);
        if(book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
        service.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso!");
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable UUID id, @Valid @RequestBody BookDto bookDto) {
        Optional<BookModel> book = service.getBookById(id);
        if(book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }

        BookModel bookUpdated = book.get();
        bookUpdated.setName(bookDto.getName());
        bookUpdated.setDescription(bookDto.getDescription());

        return ResponseEntity.status(HttpStatus.OK).body(service.update(bookUpdated));
    }
}
