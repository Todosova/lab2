package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.BookStore;

import java.util.List;

public interface BookStoreService {
    List<BookStore> findAll();
}
