package com.library.mainapp.dao.daoimpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.library.mainapp.dao.LibraryDAO;
import com.library.mainapp.model.Book;
import com.library.mainapp.model.Subject;

@Service
public class LibraryDAOImpl implements LibraryDAO {


	private FileInputStream fileInputStream = null;// for reading from file
	private ObjectInputStream objectInStream = null;// for reading from file

	private FileOutputStream fileoutStream = null;//For writing into file
	private ObjectOutputStream objectOutStream = null;//For writing into file

	private List<Subject> subjectList = null;
	private List<Book> bookList = null;

	private String bookFileName = "C:\\sts_workspace\\spring_assignment_15\\src\\main\\resources\\book_file.txt";
	private String subjectFileName = "C:\\sts_workspace\\spring_assignment_15\\src\\main\\resources\\subject_file.txt";


	public void addBook(Book book) {
		try {			
			fileoutStream = new FileOutputStream(bookFileName);
			objectOutStream = new ObjectOutputStream(fileoutStream);
			LibraryDAOImpl.addBook(fileoutStream,objectOutStream,book);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void addSubject(Subject subject) {
		try {			
			fileoutStream = new FileOutputStream(subjectFileName);
			objectOutStream = new ObjectOutputStream(fileoutStream);
			LibraryDAOImpl.addSubject(fileoutStream,objectOutStream,subject);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void searchBook(Long bookId) {
		Book book = null;
		try {
			fileInputStream = new FileInputStream(bookFileName);
			objectInStream = new ObjectInputStream(fileInputStream);				
			book = (Book)objectInStream.readObject();

			if(null != book) 
			{		if(Objects.nonNull(bookId) && bookId.equals(book.getBookId())) {
				System.out.println("Book Details:");
				System.out.println(book.toString());
			}
			}
		}
		catch(Exception e) {
			System.out.println("Sorry Book not found with given id :"+bookId);
		}

	}

	public void searchSubject(Long subjectId) {
		Subject subject = null;
		try {
			fileInputStream = new FileInputStream(subjectFileName);
			objectInStream = new ObjectInputStream(fileInputStream);

			subject = (Subject)objectInStream.readObject();

			if(null != subject) 
			{
				if(Objects.nonNull(subjectId) && subjectId.equals(subject.getSubjectId())) {
					System.out.println("Subejct Details:");
					System.out.println(subject.toString());
				}
			}
		}
		catch(Exception e) {
			System.out.println("Sorry Subejct not found in given id :"+subjectId);
		}

	}

	public void deleteBook(Long bookId) {
		Book book = null;
		try {
			fileInputStream = new FileInputStream(bookFileName);
			objectInStream = new ObjectInputStream(fileInputStream);

			book = (Book)objectInStream.readObject();
			//System.out.println(subject.toString());
			bookList = new ArrayList<Book>();
			bookList.add(book);

			if(Objects.nonNull(book) && bookId.equals(book.getBookId())) {
				bookList.remove(book);
				LibraryDAOImpl.writeBookObjectsToFile(bookFileName, bookList);
				System.out.println("Book successfully deleted with id: "+bookId);	 
			}else {
				System.out.println("Sorry Book not found in given id :"+bookId);
			}
		}
		catch(Exception e) {
			System.out.println("Sorry Subejct not found in given id :"+bookId);
		}

	}

	public void deleteSubject(Long subjectId) {
		Subject subject = null;
		try {
			fileInputStream = new FileInputStream(subjectFileName);
			objectInStream = new ObjectInputStream(fileInputStream);

			subject = (Subject)objectInStream.readObject();
			//System.out.println(subject.toString());
			subjectList = new ArrayList<Subject>();
			subjectList.add(subject);

			if(Objects.nonNull(subjectId) && subjectId.equals(subject.getSubjectId())) {
				subjectList.remove(subject);
				LibraryDAOImpl.writeSubjectObjectsToFile(subjectFileName, subjectList);
				System.out.println("Subject successfully deleted with id: "+subjectId);	 
			}else {
				System.out.println("Sorry Subejct not found in given id :"+subjectId);
			}

		}
		catch(Exception e) {
			System.out.println("Sorry Subejct not found in given id :"+subjectId);
		}
	}

	public static void addBook(FileOutputStream fileName,ObjectOutputStream out,Book book)throws IOException {
		out.writeObject(book);		 
		//out.close();
		fileName.close();
		System.out.println("Book added successfully");
	}


	public static void addSubject(FileOutputStream fileName,ObjectOutputStream out,Subject subObject)throws IOException {
		out.writeObject(subObject);	 
		//out.close();
		fileName.close();
		System.out.println("Subject added successfully");
	}
	private static void writeSubjectObjectsToFile(String filename, List<Subject> objects) throws IOException {
		OutputStream outStream = null;
		try {
			outStream = new FileOutputStream(filename);
			ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
			for (Object object : objects) {
				objectOutStream.writeObject(object);
			}
			// oos.flush();
		} finally {
			if (outStream != null) {
				outStream.close();
			}
		}
	}
	
	private static void writeBookObjectsToFile(String filename, List<Book> objects) throws IOException {
		OutputStream os = null;
		try {
			os = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			for (Object object : objects) {
				oos.writeObject(object);
			}
			// oos.flush();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

}
