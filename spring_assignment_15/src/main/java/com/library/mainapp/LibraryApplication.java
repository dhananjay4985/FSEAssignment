package com.library.mainapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.mainapp.consumer.LibraryConsumer;
import com.library.mainapp.model.Book;
import com.library.mainapp.model.Subject;

public class LibraryApplication {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("springconfiguration.xml");
		LibraryConsumer consumer = (LibraryConsumer)context.getBean("libraryConsumer");

		String subjectTitle = null;
		Long subjectId;
		Integer durationInHours;

		String bookTitle = null;
		Long bookId;
		Double bookPrice;
		Integer bookVolume;

		Book book = null;
		Date date = null;
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		BufferedReader reader = null;
		Subject subject = null;

		try {
			System.out.println("|   MENU DRIVEN CONSOLE    ");
			System.out.println("============================");
			System.out.println("| Options:               ");
			System.out.println("|        a. Add a Subject");
			System.out.println("|        b. Add a Book");
			System.out.println("|        c. Delete a Subject");
			System.out.println("|        d. Delete a book");
			System.out.println("|        e. Search for a book");
			System.out.println("|        f. Search for a subject");		
			System.out.println("|        g. Exit          ");
			System.out.println("============================");
			reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter your Choice: ");

			String choice = reader.readLine();

			switch (choice) {

			case "a":
				System.out.println("To add new subject please enter below details:");
				System.out.println("Enter subject subtitle:");
				subjectTitle = reader.readLine();

				System.out.println("Enter subject subjectId:");
				subjectId = Long.parseLong(reader.readLine());

				System.out.println("Enter duration in hour:");
				durationInHours = Integer.parseInt(reader.readLine());

				Set<Book> bookSet = new HashSet<Book>();
				System.out.println("Enter number of book references:");
				int no = Integer.parseInt(reader.readLine());

				for(int i = 0; i < no; i++) {

					System.out.println("Enter BookId:");
					bookId = Long.parseLong(reader.readLine());

					System.out.println("Enter Book Title:");
					bookTitle = reader.readLine();

					System.out.println("Enter Book Price:");
					bookPrice = Double.parseDouble(reader.readLine());

					System.out.println("Enter Book Volume:");
					bookVolume = Integer.parseInt(reader.readLine());

					System.out.println("Enter Book publish date in (DD/MM/YYYY) format:");
					String publishDate = reader.readLine();

					try {
						date = new Date(); 
						date = dateFormat.parse(publishDate);
					}
					catch(ParseException e) {
						System.out.println("Unable to parse" + publishDate);
					}				
					book = new Book();
					book.setBookId(bookId);
					book.setTitle(bookTitle);
					book.setPrice(bookPrice);
					book.setVolume(bookVolume);
					book.setPublishDate(date);
					bookSet.add(book);
				}
				subject = new Subject(subjectId,subjectTitle,durationInHours,bookSet);
				consumer.addSubejctInLibrary(subject);				
				break;

			case "b":
				System.out.println("To add new book please enter below details:");				
				System.out.println("Enter BookId:");
				bookId = Long.parseLong(reader.readLine());

				System.out.println("Enter Book Title:");
				bookTitle = reader.readLine();

				System.out.println("Enter Book Price:");
				bookPrice = Double.parseDouble(reader.readLine());

				System.out.println("Enter Book Volume:");
				bookVolume = Integer.parseInt(reader.readLine());

				System.out.println("Enter Book publish date in (DD/MM/YYYY) format:");
				String publishDate = reader.readLine();

				try {
					date = new Date(); 
					date = dateFormat.parse(publishDate);
				}
				catch(ParseException e) {
					System.out.println("Unable to parse" + publishDate);
				}				
				book = new Book();
				book.setBookId(bookId);
				book.setTitle(bookTitle);
				book.setPrice(bookPrice);
				book.setVolume(bookVolume);
				book.setPublishDate(date);
				consumer.addBookInLibrary(book);				
				break;
				
			case "c":
				System.out.println("Enter subjectId to delete:");
				subjectId = Long.parseLong(reader.readLine());
				consumer.deketeSubjectById(subjectId);		
				break;

			case "d":
				System.out.println("Enter bookId to delete:");
				bookId = Long.parseLong(reader.readLine());
				consumer.deleteBookById(bookId);		
				break;

			case "e":
				System.out.println("Enter BookId to search:");
				bookId = Long.parseLong(reader.readLine());
				consumer.searchBookById(bookId);				
				break;
				
			case "f":
				System.out.println("Enter Subject to search:");
				subjectId = Long.parseLong(reader.readLine());
				consumer.searchSubjectById(subjectId);				
				break;

			case "g":
				System.out.println("Exited successfully");
				System.exit(0);

			}//switch
		}//try
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception while processing choice:");
		}

	}
}