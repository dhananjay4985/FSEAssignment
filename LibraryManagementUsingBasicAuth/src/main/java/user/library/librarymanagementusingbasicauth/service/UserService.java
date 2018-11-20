package user.library.librarymanagementusingbasicauth.service;

import user.library.librarymanagementusingbasicauth.model.User;

public interface UserService {
	
	boolean userLogin(String email,String password);
	void addUser(User user);
}
