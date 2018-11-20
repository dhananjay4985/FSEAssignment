package user.library.librarymanagementusingbasicauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import user.library.librarymanagementusingbasicauth.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	User findUserByEmail(String email);
}
