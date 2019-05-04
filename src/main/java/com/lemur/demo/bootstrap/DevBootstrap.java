package com.lemur.demo.bootstrap;

import com.lemur.demo.model.Author;
import com.lemur.demo.model.Book;
import com.lemur.demo.model.Publisher;
import com.lemur.demo.repositories.AuthorRepository;
import com.lemur.demo.repositories.BookRepository;
import com.lemur.demo.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){

        Publisher publisher = new Publisher();
        publisher.setName("foo");

        publisherRepository.save(publisher);

        //Eric
        Author eric = new Author("Eric", "Evans");
        Book book = new Book("Domain Driven Design","12345", publisher);
        eric.getBooks().add(book);
        book.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(book);

        //Rob
        Author rob = new Author("Rob", "Johnson");
        Book bookEJB = new Book("J2EE Development", "234234", publisher);
        rob.getBooks().add(bookEJB);

        authorRepository.save(rob);
        bookRepository.save(bookEJB);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
