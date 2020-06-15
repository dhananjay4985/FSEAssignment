package user.library.librarymanagementusingbasicauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import user.library.librarymanagementusingbasicauth.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{

}
