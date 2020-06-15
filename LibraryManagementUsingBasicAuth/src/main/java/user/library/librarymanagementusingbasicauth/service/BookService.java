package user.library.librarymanagementusingbasicauth.service;

import java.util.List;

import user.library.librarymanagementusingbasicauth.model.Book;

public interface BookService {

	void createBook(Book book);
	List<Book> getBook();
	Book findById(Long bookId);
	void update(Book user, Long bookId);
	void deleteBookById(Long bookId);
}
