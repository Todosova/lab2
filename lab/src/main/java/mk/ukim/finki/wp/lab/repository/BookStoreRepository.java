package mk.ukim.finki.wp.lab.repository;


import mk.ukim.finki.wp.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookStoreRepository {

    List<BookStore> listBookStores = new ArrayList<>();

    public BookStoreRepository(List<BookStore> listBookStores) {
        listBookStores.add(new BookStore("Three", "Skopje", "City Trade Center"));
        listBookStores.add(new BookStore("Akademska kniga", "Skopje", "Partizanski Odredi br23"));
        listBookStores.add(new BookStore("ABC", "Kumanovo", "Tode Mendol br12"));
        listBookStores.add(new BookStore("Zbor", "Veles", "Petre Alchev br1"));
        listBookStores.add(new BookStore("Prosvetno delo", "Shtip", "Vancho Prke"));
        this.listBookStores = listBookStores;
    }

    public List<BookStore> findAll(){
        return listBookStores;
    }
}
