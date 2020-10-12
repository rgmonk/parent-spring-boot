package de.dp.sample.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.dp.sample.model.Book;

import de.dp.sample.repository.BookRepository;

@Service
public class BookService {
 
    @Autowired
    private BookRepository bookRepository;
 
    public List<Book> list() {
        return bookRepository.findAll();
    }
}