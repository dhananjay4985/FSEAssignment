package user.library.librarymanagementusingbasicauth.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import user.library.librarymanagementusingbasicauth.exception.ResourceNotFoundException;
import user.library.librarymanagementusingbasicauth.model.Book;
import user.library.librarymanagementusingbasicauth.repository.BookRepository;
import user.library.librarymanagementusingbasicauth.repository.SubjectRepository;
import user.library.librarymanagementusingbasicauth.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {

	    @Autowired
	 	private BookRepository bookRepository;
	    
	    @Autowired
	    private SubjectRepository subjectRepository;
	    
		@Override
		public void createBook(Book saveBook) {
			bookRepository.save(saveBook);
		}

		@Override
		public Book findById(Long id) {		
			Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", id));		
			return book;
		}

		@Override
		public List<Book> getBook() {
			return bookRepository.findAll();
		
		}

		@Override
		public void update(Book updateBook, Long bookId) {		
			Book book = bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Book","BookIs",bookId));
			book.setBookId(updateBook.getBookId());
			book.setPrice(updateBook.getPrice());
			book.setPublishDate(updateBook.getPublishDate());
			book.setTitle(updateBook.getTitle());
			book.setVolume(updateBook.getVolume());		
			book.setSubject(subjectRepository.getOne(updateBook.getSubjectId()));
			bookRepository.save(book);
		}

		@Override
		public void deleteBookById(Long bookId) {
			Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", bookId));
			bookRepository.delete(book);
		}
}
