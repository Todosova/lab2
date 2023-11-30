package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookStore;
import mk.ukim.finki.wp.lab.service.impl.BookStoreServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    List<Book> books = new ArrayList<>();
    private BookStoreServiceImpl bookStoreService;

    public BookRepository(List<Book> books, BookStoreServiceImpl bookStoreService) {
        this.bookStoreService= bookStoreService;
        List<BookStore> bookStores = this.bookStoreService.findAll();
        books.add(new Book("isbn1", "Pirej", "Istoriski", 1980, bookStores.get(0).getId(), bookStores.get(0)));
        books.add(new Book("isbn2", "Belata dolina", "Poman", 1962, bookStores.get(1).getId(), bookStores.get(1)));
        books.add(new Book("isbn3", "Vezilka", "Proza", 1955, bookStores.get(2).getId(), bookStores.get(2)));
        books.add(new Book("isbn4", "Mladi Maj", "Poezija", 1994, bookStores.get(3).getId(), bookStores.get(3)));
        books.add(new Book("isbn5", "T'ga za jug", "Prerodba", 1850, bookStores.get(4).getId(), bookStores.get(4)));
        this.books = books;
    }
    public List<Book> findAll()
    {
        return books;
    }

    public Book findByIsbn(String isbn){
        return books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    Author addAuthorToBook(Author author, Book book){
        book.getAuthors().add(author);

        return author;
    }


}
