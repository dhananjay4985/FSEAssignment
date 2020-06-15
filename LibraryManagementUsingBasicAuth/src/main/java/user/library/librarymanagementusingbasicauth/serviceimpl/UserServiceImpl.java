package user.library.librarymanagementusingbasicauth.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import user.library.librarymanagementusingbasicauth.model.User;
import user.library.librarymanagementusingbasicauth.repository.UserRepository;
import user.library.librarymanagementusingbasicauth.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;	
	
	@Override
	public boolean userLogin(String email, String password) {
		boolean login = false;
		User user = userRepository.findUserByEmail(email);
		if(null!=user) {
			if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
				login = true;
			}
		}
		return login;
	}

	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}
	
}
