package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements AuthorService, BookService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return listAuthors().stream().filter(author -> author.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }
    @Override
    public Book findBookByIsbn(String isbn) {
        return listBooks().stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElse(null);
    }
    public List<Book> addNewBook(Book book){
        List<Book> bookList = listBooks();
        bookList.add(book);
        return bookList;
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Author author = findById(authorId);
        Book book = findBookByIsbn(isbn);

        if (book != null){
            book.getAuthors().add(author);
        }

        return author;
    }
    public Author addBookToAuthor(String isbn, Long Id){
        Book book = findBookByIsbn(isbn);
        Author author = findById(Id);

        String bookTitle = book.getTitle();

        book.setTitle("Copy of " + bookTitle);

        author.getBookList().add(book);

        return author;
    }
}

