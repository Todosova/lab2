package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.repository.BookStoreRepository;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.validation.BindingResult;

public class BookController {

    private BookRepository bookRepository;
    private BookStoreRepository bookStoreRepository;

    public BookController(BookRepository bookRepository, BookStoreRepository bookStoreRepository) {
        this.bookRepository = bookRepository;
        this.bookStoreRepository = bookStoreRepository;
    }
    @RequestMapping("/books")
    public String getBooksPage(@RequestParam(required = false) String error, Model model){

        model.addAttribute("books", bookRepository.findAll());

        return "listBooks.html";
    }
    @RequestMapping("/books/add")
    public String addBookView (@RequestParam(required = false) String error, Model model){

        model.addAttribute("bookStores", bookStoreRepository.findAll());
        model.addAttribute("bookForm", new Book());

        return "add-book.html";
    }

    @PostMapping("/books/save")
    public String saveBook(Book bookForm, BindingResult result, Model model)  {
        String isbn = bookForm.getIsbn();
        String title = bookForm.getTitle();
        String genre = bookForm.getGenre();
        Integer year = bookForm.getYear();
        Long selectedBookStoreId = bookForm.getBookStoreId();

        BookStore selectedBookStore = (BookStore) bookStoreRepository.findAll().stream().filter(bookStore1 -> bookStore1.getId().equals(selectedBookStoreId)).findFirst().orElse(null);

        List<Book> existingBooks = bookRepository.findAll();
        existingBooks.add(new Book(isbn, title, genre, year, selectedBookStoreId, selectedBookStore));

        model.addAttribute("books", existingBooks);

        return "listBooks.html";
    }

    @GetMapping("/books/edit/{bookId}")
    public String editBook(@PathVariable Long bookId, Model model){

        Book foundBook = bookRepository.findAll().stream().filter(book1 -> book1.getId().equals(bookId)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Book id: " + bookId));

        model.addAttribute("book", foundBook);

        return "editBook.html";
    }

    @PostMapping("/books/edit")
    public String editedBook(Book bookForm, Model model){
        String isbn = bookForm.getIsbn();
        String title = bookForm.getTitle();
        String genre = bookForm.getGenre();
        Integer year = bookForm.getYear();

        Book book = bookRepository.findAll().stream().filter(book1 -> book1.getIsbn().equals(isbn)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Book id: " + isbn));

        book.setTitle(title);
        book.setGenre(genre);
        book.setYear(year);

        model.addAttribute("books", bookRepository.findAll());

        return "listBooks.html";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id, Model model){

        bookRepository.findAll().removeIf(book -> book.getId().equals(id));

        model.addAttribute("books", bookRepository.findAll());

        return "listBooks.html";
    }
}
