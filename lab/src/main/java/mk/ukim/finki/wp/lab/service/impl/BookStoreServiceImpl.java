package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.BookStore;
import mk.ukim.finki.wp.lab.service.BookStoreService;
import mk.ukim.finki.wp.lab.repository.BookStoreRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BookStoreServiceImpl implements BookStoreService {
    private final BookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }
}