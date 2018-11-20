package user.library.librarymanagementusingbasicauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import user.library.librarymanagementusingbasicauth.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
