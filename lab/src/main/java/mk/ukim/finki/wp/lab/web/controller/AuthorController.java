package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.service.impl.BookServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


    @Controller
    public class AuthorController {

        private BookServiceImpl bookService;

        public AuthorController(BookServiceImpl bookService) {
            this.bookService = bookService;
        }

        @GetMapping("/author/copyBooks/{authorId}")
        public String copyAuthor(@PathVariable Long authorId, Model model){

            Author author = bookService.listAuthors().stream().filter(author1 -> author1.getId().equals(authorId)).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No valid id for author: " + authorId));

            for (int i = 0; i < author.getBookList().size(); i++){
                bookService.listBooks().add(author.getBookList().get(i));
            }

            model.addAttribute("books", bookService.listBooks());

            return "listBooks.html";
        }

    }

