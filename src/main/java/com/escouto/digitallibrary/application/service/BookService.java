package com.escouto.digitallibrary.application.service;

import com.escouto.digitallibrary.presentation.dto.BookDTO;
import com.escouto.digitallibrary.presentation.dto.BookFilterDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    /**
     * Retrieves all books from the database.
     *
     * @return a list of all books
     */
    List<BookDTO> getAllBooks();

    /**
     * Retrieves a book by its ID.
     *
     * @param id the ID of the book to retrieve
     * @return an Optional containing the book, or empty if not found
     */
    Optional<BookDTO> getBookById(Long id);

    /**
     * Saves a new book or updates an existing one.
     *
     * @param bookDTO the DTO representing the book to save or update
     * @return the saved or updated book DTO
     */
    BookDTO saveBook(BookDTO bookDTO);

    /**
     * Deletes a book by its ID.
     *
     * @param id the ID of the book to delete
     */
    void deleteBook(Long id);

    /**
     * Checks if a book with the given ID exists.
     *
     * @param id the ID of the book to check
     * @return true if the book exists, false otherwise
     */
    boolean existsById(Long id);

    /**
     * Searches for books based on the provided filter criteria.
     * @param filter the filter criteria
     * @return a list of books matching the filter criteria
     */
    List<BookDTO> searchBooks(BookFilterDTO filter);
}