package com.hibernate.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import com.hibernate.bo.Book;
import com.hibernate.bo.Subject;
import com.hibernate.service.BookService;
import com.hibernate.service.SubjectService;


public class HibernateDAOApplcation {

	public static void main(String[] args) {		
		String subjectTitle = null;
		int subjectId;
		int durationInHours;

		String bookTitle = null;
		int bookId;
		double bookPrice;
		int bookVolume;

		Book book = null;
		Date date = null;
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		BufferedReader reader = null;
		Subject subject = null;
		SubjectService subjectService = null;
		BookService bookService = null;
		try {
			System.out.println("|   MENU DRIVEN CONSOLE    ");
			System.out.println("============================");
			System.out.println("| Options:               ");
			System.out.println("|        a. Add a Subject");
			System.out.println("|        b. Add a Book");
			System.out.println("|        c. Delete a Subject");
			System.out.println("|        d. Delete a book");
			System.out.println("|        e. Search for a subject");
			System.out.println("|        f. Search for a Book");		
			System.out.println("|        g. Sort Book by Title");
			System.out.println("|        h. Sort Subject By Title");
			System.out.println("|        i. Sort Book by Publish Date");
			System.out.println("|        j. Exit          ");
			System.out.println("============================");
			reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter your Choice: ");

			String choice = reader.readLine();

			switch (choice) {

			case "a":
				System.out.println("To add new subject please enter below details:\n");
				System.out.println("................................................");
				subjectService = new SubjectService();
								
				System.out.println("Enter subject subjectId:");
				subjectId = Integer.parseInt(reader.readLine());
				//subject.setSubjectId(subjectId);
				
				System.out.println("Enter duration in hour:");
				durationInHours = Integer.parseInt(reader.readLine());
				//subject.setDurationInHours(durationInHours);
				
				System.out.println("Enter subject subtitle:");
				subjectTitle = reader.readLine();
				//subject.setSubjectTitle(subjectTitle);		

				Set<Book> bookSet = new HashSet<Book>();
				
				System.out.println("Enter number of book references:");
				int no = Integer.parseInt(reader.readLine());
				
				for(int i = 0; i < no; i++) {
					book = new Book();
					
					System.out.println("Enter BookId:");
					bookId = Integer.parseInt(reader.readLine());
					book.setBookId(bookId);
					
					System.out.println("Enter Book Price:");
					bookPrice = Double.parseDouble(reader.readLine());
					book.setPrice(bookPrice);
					
					System.out.println("Enter Book publish date in (DD/MM/YYYY) format:");
					String publishDate = reader.readLine();
					try {
						date = new Date(); 
						date = dateFormat.parse(publishDate);
						book.setPublishDate(date);
					}
					catch(ParseException e) {
						System.out.println("Unable to parse" + publishDate);
					}		
					System.out.println("Enter Book Title:");
					bookTitle = reader.readLine();
					book.setTitle(bookTitle);
					
					System.out.println("Enter Book Volume:");
					bookVolume = Integer.parseInt(reader.readLine());
					book.setVolume(bookVolume);
					
					bookSet.add(book);
				}				
				subject = new Subject(subjectId, subjectTitle, durationInHours, bookSet);				
				subjectService.addSubejct(subject);				
				break;
				
			case "b":
				System.out.println("To add new book please enter below details:\n");
				System.out.println("................................................");
				book = new Book();
				bookService = new BookService();
				
				System.out.println("Enter BookId:");
				bookId = Integer.parseInt(reader.readLine());
				book.setBookId(bookId);
				
				System.out.println("Enter Book Price:");
				bookPrice = Double.parseDouble(reader.readLine());
				book.setPrice(bookPrice);
				
				System.out.println("Enter Book publish date in (DD/MM/YYYY) format:");
				String publishDate = reader.readLine();
				try {
					date = new Date(); 
					date = dateFormat.parse(publishDate);
					book.setPublishDate(date);
				}
				catch(ParseException e) {
					System.out.println("Unable to parse" + publishDate);
				}		
				System.out.println("Enter Book Title:");
				bookTitle = reader.readLine();
				book.setTitle(bookTitle);
				
				System.out.println("Enter Book Volume:");
				bookVolume = Integer.parseInt(reader.readLine());
				book.setVolume(bookVolume);
				bookService.addBook(book);
				break;
				
			case "c":
				System.out.println("Enter SubejctId to delete:");
				subjectId = Integer.parseInt(reader.readLine());
				subjectService = new SubjectService();
				subjectService.deleteSubjectById(subjectId);			
				break;
				
			case "d":
				System.out.println("Enter BookId to delete:");
				bookId = Integer.parseInt(reader.readLine());
				bookService = new BookService();
				bookService.deleteBookById(bookId);			
				break;
				
			case "e":
				System.out.println("Enter SubjectId to search:");
				subjectId = Integer.parseInt(reader.readLine());
				subjectService = new SubjectService();
				subjectService.searchSubject(subjectId);	
				break;
				
			case "f":
				System.out.println("Enter BookId to Search:");
				bookId = Integer.parseInt(reader.readLine());
				bookService = new BookService();
				bookService.searchBook(bookId);			
				break;
				
			case "g":
				bookService = new BookService();
				bookService.sortBookByTitle();
				break;
				
			case "h":
				subjectService = new SubjectService();
				subjectService.sortSubjectByTitle();
				break;
			
			case "i":
				bookService = new BookService();
				bookService.sortBookByPublishDate();
				break;
				
			case "j":
				System.out.println("Exited successfully");
				System.exit(0);
			}

		}
		catch(Exception e) {
			e.printStackTrace();
			//System.out.println("Exception while processing choice:"+e.getMessage());
		}
	}
}
