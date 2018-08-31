package com.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bo.Book;
import com.bo.Subject;

public class SubjectBookMain {

	public static void addBook(FileOutputStream fileName,ObjectOutputStream out,String bookName,String author)throws IOException {
		Book book = new Book();
		book.setBookName(bookName);
		book.setAuthor(author);
		out.writeObject(book);		 
		//out.close();
		fileName.close();
        System.out.println("Book inserted successfully");
        System.out.println(book.getAuthor() +" :: "+book.getBookName());
	}

	
	public static void addSubject(FileOutputStream fileName,ObjectOutputStream out,Subject subObject)throws IOException {
		out.writeObject(subObject);	 
		//out.close();
		fileName.close();
        System.out.println("Subject inserted successfully");
	}
	
	public static void deleteSubject() {
		
	}
	
	//delete subject
	private static void writeSubjectObjectsToFile(String filename, List<Subject> objects) throws IOException {
	    System.out.println("Writing sub object in file");
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
	//delete book 
	private static void writeBookObjectsToFile(String filename, List<Book> objects) throws IOException {
	    System.out.println("Writing sub object in file");
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
	public static void main(String[] args) {

		String bookFileName = "C:\\Users\\594665\\eclipse-workspace\\Core Java Assignment 08\\resources\\book_file.txt";
		String subjectFileName = "C:\\Users\\594665\\eclipse-workspace\\Core Java Assignment 08\\resources\\subject_file.txt";
		
		String subName = null;
		String bookAuthor = null;
		Long subjectId;
		int durationInHours;
	
		ObjectInputStream outIn;
		FileInputStream fileIp;//input
		
		FileOutputStream file = null;//output
		ObjectOutputStream out = null;
		
		FileOutputStream file1 = null;
		ObjectOutputStream out1 = null;
		
		List<Subject> objectList = null;
		List<Book> bobjectList = null;
		
		Book book;
		Subject sub;
		
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
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter your Choice: ");

			String choice = reader.readLine();

			switch (choice) {	
			
			case "a":
				System.out.println("Enter subject subtitle:");
			    subName = reader.readLine();
				
				System.out.println("Enter subject subjectId:");
				subjectId = Long.parseLong(reader.readLine());
				
				System.out.println("Enter duration in hour:");
				durationInHours = Integer.parseInt(reader.readLine());
				
				//
				Set<Book> bookSet = new HashSet<Book>();
				System.out.println("Enter number of book references:");
				int no = Integer.parseInt(reader.readLine());
				
				for(int i = 0; i < no; i++) {
					System.out.println("Enter Book Name");
					String refBookName = reader.readLine();
					System.out.println("Enter Author");
					bookAuthor = reader.readLine();
					
					book = new Book();
					book.setBookName(refBookName);
					book.setAuthor(bookAuthor);
					bookSet.add(book);
				}
				Subject subject = new Subject(subjectId,subName,durationInHours,bookSet);
				file1 = new FileOutputStream(subjectFileName);
				out1 = new ObjectOutputStream(file1);
				SubjectBookMain.addSubject(file1, out1, subject);
				System.out.println("Subject done");
				break;
				
			case "b": 	
				System.out.println("Enter Book Name");
				String bookName = reader.readLine();
				System.out.println("Enter Author");
				String author = reader.readLine();
				file = new FileOutputStream(bookFileName);
				out = new ObjectOutputStream(file);
				SubjectBookMain.addBook(file,out,bookName,author);
				break;
			
			case "c":
				sub = null;
				
				System.out.println("Enter subjectId to delete:");
				
				subjectId = Long.parseLong(reader.readLine());
				
				fileIp = new FileInputStream(subjectFileName);
	            outIn = new ObjectInputStream(fileIp);
	            
	            System.out.println("Read subject from file");
	            sub = (Subject)outIn.readObject();
	            objectList = new ArrayList<Subject>();
	            objectList.add(sub);
	            
	            if(sub.getSubjectId() == subjectId) {
	            	System.out.println("Subject id found :"+subjectId+" : "+sub.getSubtitle());
	            	objectList.remove(sub);
	            }
	            System.out.println("Write remaining after deleting 1");
	            SubjectBookMain.writeSubjectObjectsToFile(subjectFileName, objectList);
	            
				break;
			case "d":
				book = null;
				System.out.println("Enter bookname to delete:");
				bookName = reader.readLine();
				fileIp = new FileInputStream(bookFileName);
	            outIn = new ObjectInputStream(fileIp);
	            System.out.println("Read book from file");
				book = (Book)outIn.readObject();
				bobjectList = new ArrayList<Book>();
		        bobjectList.add(book);
		        
		        if(null!= bookName && book.getBookName().equals(bookName)) {
	            	System.out.println("deleting book details :"+book.getBookName()+" :"+book.getAuthor());
	            	bobjectList.remove(book);
	            }
	            System.out.println("Write remaining books after deleting ");
	            SubjectBookMain.writeBookObjectsToFile(bookFileName, bobjectList);
				break;
			case "e":
				book = null;
				System.out.println("Enter bookname to Search:");
				bookName = reader.readLine();
				fileIp = new FileInputStream(bookFileName);
	            outIn = new ObjectInputStream(fileIp);
	            System.out.println("Read book from file");
				book = (Book)outIn.readObject();
				bobjectList = new ArrayList<Book>();
		        bobjectList.add(book);
		        
		        if(null!= bookName && book.getBookName().equals(bookName)) {
	            	System.out.println("Book found with details :"+book.getBookName()+" :"+book.getAuthor());
	            	bobjectList.remove(book);
	            }
				break;
			
			case "f":
				sub = null;
				
				System.out.println("Enter subjectId to Search:");
				
				subjectId = Long.parseLong(reader.readLine());
				
				fileIp = new FileInputStream(subjectFileName);
	            outIn = new ObjectInputStream(fileIp);
	            
	            System.out.println("Read subject from file");
	            sub = (Subject)outIn.readObject();
	            objectList = new ArrayList<Subject>();
	            objectList.add(sub);
	            
	            if(sub.getSubjectId() == subjectId) {
	            	System.out.println("Subject details found :"+subjectId+" : "+sub.getSubtitle()+" : "+sub.getDurationInHours()+" :"+
	            			sub.getBookSet().iterator().next().getAuthor() +" : "+sub.getBookSet().iterator().next().getBookName());
	            	objectList.remove(sub);
	            }
				break;
			default:
				// The user input an unexpected choice.
			}

		}
		catch(Exception e) {
			System.out.println("Exception "+e);
		}
	}
}
