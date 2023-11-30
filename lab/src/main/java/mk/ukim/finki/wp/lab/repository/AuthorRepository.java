package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {

    List<Author> authors = new ArrayList<>();
    public AuthorRepository(List<Author> authors) {
        authors.add(new Author(1L, "Petre ", "Andreevski", "Makedonski poet, romanopisec, raskazuvach i dramski avtor."));
        authors.add(new Author(2L, "Simon ", "Drakul", "Makedonski raskazuvach, romanopisec, dramski avtor, scenarist, istorichar i preveduvach."));
        authors.add(new Author(3L, "Blaze ", "Konevski", "Istaknat makedonski knizeven, kulturen i javen rabotnik, akademik, poet, eseist, knizeven istoricar, profesor..."));
        authors.add(new Author(4L, "Sonja ", "Mandjuk", "Makedonska poetesa i raskazuvach."));
        authors.add(new Author(5L, "Konstadin ", "Miladinov", "Makedonski sobirach na narodni umotvorbi i poet."));
        this.authors = authors;
    }
    public List<Author> findAll()
    {
        return authors;

    }
    public Optional<Author> findById(Long id){
        return authors.stream().filter(author -> author.getId().equals(id)).findFirst();
    }
}
