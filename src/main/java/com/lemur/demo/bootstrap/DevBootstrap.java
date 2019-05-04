package com.lemur.demo.bootstrap;

import com.lemur.demo.model.Author;
import com.lemur.demo.model.Book;
import com.lemur.demo.repositories.AuthorRepository;
import com.lemur.demo.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private void initData(){

        //Eric
        Author eric = new Author("Eric", "Evans");
        Book book = new Book("Domain Driven Design","12345", "Harper Collins");
        eric.getBooks().add(book);
        book.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(book);

        //Rob
        Author rob = new Author("Rob", "Johnson");
        Book bookEJB = new Book("J2EE Development", "234234", "Worx");
        rob.getBooks().add(bookEJB);

        authorRepository.save(rob);
        bookRepository.save(bookEJB);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
