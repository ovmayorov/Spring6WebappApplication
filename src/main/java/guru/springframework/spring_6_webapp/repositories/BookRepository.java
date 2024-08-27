package guru.springframework.spring_6_webapp.repositories;

import guru.springframework.spring_6_webapp.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}
