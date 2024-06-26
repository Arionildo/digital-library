package com.escouto.digitallibrary.presentation.controller;

import com.escouto.digitallibrary.application.service.BookService;
import com.escouto.digitallibrary.presentation.dto.BookDTO;
import com.escouto.digitallibrary.presentation.dto.BookFilterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Management")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "Get all books")
    public ResponseEntity<List<EntityModel<BookDTO>>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        List<EntityModel<BookDTO>> bookModels = books.stream()
                .map(this::addSelfLink)
                .toList();
        return ResponseEntity.ok(bookModels);
    }

    @GetMapping("/search")
    @Operation(summary = "Search for books based on filter criteria")
    public ResponseEntity<List<EntityModel<BookDTO>>> searchBooks(BookFilterDTO filter) {
        List<BookDTO> books = bookService.searchBooks(filter);
        List<EntityModel<BookDTO>> bookModels = books.stream()
                .map(this::addSelfLink)
                .toList();
        return ResponseEntity.ok(bookModels);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID")
    public ResponseEntity<EntityModel<BookDTO>> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(this::addAllBooksLink)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new book")
    public ResponseEntity<EntityModel<BookDTO>> createBook(@RequestBody BookDTO bookDTO) {
        BookDTO savedBookDTO = bookService.saveBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addSelfLink(savedBookDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by ID")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.existsById(id)) {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private EntityModel<BookDTO> addSelfLink(BookDTO bookDTO) {
        return EntityModel.of(bookDTO,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(bookDTO.getId())).withSelfRel());
    }

    private EntityModel<BookDTO> addAllBooksLink(BookDTO bookDTO) {
        return EntityModel.of(bookDTO,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("all"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).searchBooks(new BookFilterDTO())).withRel("filter"));
    }
}
