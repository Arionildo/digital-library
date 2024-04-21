package com.escouto.digitallibrary.service;

import com.escouto.digitallibrary.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    /**
     * Retrieves all books from the database.
     * @return a list of all books
     */
    List<Book> getAllBooks();

    /**
     * Retrieves a book by its ID.
     * @param id the ID of the book to retrieve
     * @return an Optional containing the book, or empty if not found
     */
    Optional<Book> getBookById(Long id);

    /**
     * Saves a new book or updates an existing one.
     * @param book the book to save or update
     * @return the saved or updated book
     */
    Book saveBook(Book book);

    /**
     * Deletes a book by its ID.
     * @param id the ID of the book to delete
     */
    void deleteBook(Long id);

    /**
     * Checks if a book with the given ID exists.
     * @param id the ID of the book to check
     * @return true if the book exists, false otherwise
     */
    boolean existsById(Long id);
}
