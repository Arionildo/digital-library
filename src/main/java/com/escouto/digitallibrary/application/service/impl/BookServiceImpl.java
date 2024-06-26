package com.escouto.digitallibrary.application.service.impl;

import com.escouto.digitallibrary.application.mapper.BookMapper;
import com.escouto.digitallibrary.application.service.BookService;
import com.escouto.digitallibrary.domain.entity.Book;
import com.escouto.digitallibrary.infrastructure.persistence.BookRepository;
import com.escouto.digitallibrary.infrastructure.util.CacheNames;
import com.escouto.digitallibrary.presentation.dto.BookDTO;
import com.escouto.digitallibrary.presentation.dto.BookFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.escouto.digitallibrary.domain.specification.BookSpecification.authorContains;
import static com.escouto.digitallibrary.domain.specification.BookSpecification.titleContains;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CacheNames.BOOKS, key = "#filter.hashCode()")
    public List<BookDTO> searchBooks(BookFilterDTO filter) {
        Specification<Book> specification = Specification
                .where(titleContains(filter.getTitle()))
                .and(authorContains(filter.getAuthor()));

        List<Book> filteredBooks = bookRepository.findAll(specification);
        return filteredBooks.stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CacheNames.BOOKS)
    public List<BookDTO> getAllBooks() {
        Iterable<Book> booksIterable = bookRepository.findAll();
        List<Book> booksList = StreamSupport.stream(booksIterable.spliterator(), false)
                .toList();
        return booksList.stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CacheNames.BOOKS, key = "#id")
    public Optional<BookDTO> getBookById(Long id) {
        return bookRepository.findById(id).map(bookMapper::toDTO);
    }

    @Override
    @Transactional
    @CacheEvict(value = CacheNames.BOOKS, allEntries = true)
    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        return bookMapper.toDTO(bookRepository.save(book));
    }

    @Override
    @Transactional
    @CacheEvict(value = CacheNames.BOOKS, key = "#id")
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }
}