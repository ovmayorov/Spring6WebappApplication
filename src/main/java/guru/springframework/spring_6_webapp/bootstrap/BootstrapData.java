package guru.springframework.spring_6_webapp.bootstrap;

import guru.springframework.spring_6_webapp.domain.Author;
import guru.springframework.spring_6_webapp.domain.Book;
import guru.springframework.spring_6_webapp.domain.Publisher;
import guru.springframework.spring_6_webapp.repositories.AuthorRepository;
import guru.springframework.spring_6_webapp.repositories.BookRepository;
import guru.springframework.spring_6_webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved); //creating associating author and book
        rodSaved.getBooks().add(noEJBSaved);

        authorRepository.save(ericSaved); // saving association to H2 database
        bookRepository.save(noEJBSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author    count: " + authorRepository.count());
        System.out.println("Book      count: " + bookRepository.count());

        Publisher publisher = new Publisher();
        publisher.setPublisherName("My Publisher");
        publisher.setAddress("123 Main St.");
        publisher.setCity("Chicago");
        publisher.setState("Illinois");
        publisher.setZipCode("60605");

        publisherRepository.save(publisher);

        System.out.println("Publisher count: " + publisherRepository.count());

    }
}
