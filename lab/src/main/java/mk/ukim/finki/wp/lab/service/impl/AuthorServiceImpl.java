package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    public AuthorServiceImpl(AuthorRepository authorRepository)
    {
        this.authorRepository = authorRepository;
    }
    @Override
    public List<Author> listAuthors() {
         return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return listAuthors().stream().filter(author -> author.getId().equals(id)).findFirst().orElse(null);
    }
}
