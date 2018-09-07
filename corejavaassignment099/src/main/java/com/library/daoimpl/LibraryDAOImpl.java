package com.library.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.library.bo.Book;
import com.library.bo.Subject;
import com.library.constants.Constants;
import com.library.dao.LibraryDAO;

public class LibraryDAOImpl implements LibraryDAO {

	private DataSource dataSource;
	private PreparedStatement pstmtSubject = null;
	private PreparedStatement pstmtBook = null;
	private Connection connection = null;
	private ResultSet resultSet;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void addBook(Book book) {
		//int commonSubjectId = 12450;
		try {
			connection = dataSource.getConnection();
			pstmtBook = connection.prepareStatement(Constants.INSERT_ONLYBOOK);		
			if(null != book) {				
				pstmtBook.setInt(1, book.getBookId());
				pstmtBook.setString(2, book.getTitle());
				pstmtBook.setDouble(3, book.getPrice());
				java.sql.Date sqlDate = new java.sql.Date(book.getPublishDate().getTime()); 
				pstmtBook.setInt(4, book.getVolume());
				pstmtBook.setDate(5, sqlDate);
				//pstmtBook.setInt(6, commonSubjectId);				
				pstmtBook.executeUpdate();					

			}
			pstmtBook.close();			
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			//throw new RuntimeException(e);

		}//catch 
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}//finally
	}//addBook

	@Override
	public void addSubject(Subject subject) {		
		try {
			connection = dataSource.getConnection();
			pstmtSubject = connection.prepareStatement(Constants.INSERT_SUBJECT);
			pstmtBook = connection.prepareStatement(Constants.INSERT_BOOK);		

			pstmtSubject.setInt(1, subject.getSubjectId());
			pstmtSubject.setString(2, subject.getSubjectTitle());
			pstmtSubject.setInt(3, subject.getDurationInHours());
			pstmtSubject.executeUpdate();		
			pstmtSubject.close();

			if(null != subject) {

				for(Book book : subject.getBookSet()) {	

					pstmtBook.setInt(1, book.getBookId());
					pstmtBook.setString(2, book.getTitle());
					pstmtBook.setDouble(3, book.getPrice());
					java.sql.Date sqlDate = new java.sql.Date(book.getPublishDate().getTime()); 
					pstmtBook.setInt(4, book.getVolume());
					pstmtBook.setDate(5, sqlDate);
					pstmtBook.setInt(6, subject.getSubjectId());
					//System.out.println(book.toString() +":: "+ subject.getSubjectId());
					pstmtBook.executeUpdate();					
				}				
			}
			pstmtBook.close();			
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			//throw new RuntimeException(e);

		}//catch 
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}//finally
	}//addSubject

	@Override
	public void searchBook(long bookId) {
		try {
			connection = dataSource.getConnection();			
			pstmtBook = connection.prepareStatement(Constants.SELECT_BOOK);		
			pstmtBook.setLong(1, bookId);
			resultSet = pstmtBook.executeQuery();
			if (resultSet.isBeforeFirst()) {
				if (resultSet.isBeforeFirst()) {				
					while (resultSet.next()) {
						String tile = resultSet	.getString("title");
						int price  = resultSet.getInt("price");
						String publishdate = resultSet.getDate("publishdate").toString();
						System.out.println("Book title: "+tile+",price: "+price+",Publish Date: "+publishdate);
					}
				}
			}
			else {
				System.out.println("No records for this book");
			}	
		}
		catch (SQLException e) {
			System.out.println("catch");
			//System.out.println(e.getStackTrace());
			//throw new RuntimeException(e);

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("inside 2nd catch");
					System.out.println(e.getMessage());
				}
			}
		}
	}

	@Override
	public void searchSubject(long subjectId) {		
		try {
			connection = dataSource.getConnection();			
			pstmtSubject = connection.prepareStatement(Constants.SELECT_SUBJECT);		
			pstmtSubject.setLong(1, subjectId);
			resultSet = pstmtSubject.executeQuery();

			if (resultSet.isBeforeFirst()) {				
				while (resultSet.next()) {
					int subjId = resultSet.getInt("subjectid");
					String subTitle = resultSet	.getString("subjecttitle");
					int duration = resultSet.getInt("durationInHours");
					String tile = resultSet	.getString("title");
					int price  = resultSet.getInt("price");
					String publishdate = resultSet.getDate("publishdate").toString();
					System.out.println("below are the subject details:");

					System.out.println("SubjectId: "+subjId +",Subject Title:"+subTitle +
							",Duration of course: "+duration+"Hrs"+",Book title: "+tile+",price: "+price+",Publish Date: "+publishdate);
				}
			}else {
				System.out.println("No records for this subject");
			}			
			pstmtBook.close();			
		}
		catch (SQLException e) {
			System.out.println("catch");
			//System.out.println(e.getStackTrace());
			//throw new RuntimeException(e);

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {					
					System.out.println(e.getMessage());
				}
			}
		}
	}

	@Override
	public void deleteBook(long bookId) {
		int n = 0;
		try {
			connection = dataSource.getConnection();			
			pstmtBook = connection.prepareStatement(Constants.DELETE_BOOK);		
			pstmtBook.setLong(1, bookId);
			n = pstmtBook.executeUpdate();			
			pstmtBook.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
			//throw new RuntimeException(e);

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		if(n==0) {
			System.out.println("No record to delete");
		}
		else {
			System.out.println("Book successfully deleted");
		}
	}

	@Override
	public void deleteSubject(int subjectId) {
		int n = 0;
		try {
			connection = dataSource.getConnection();
			pstmtSubject = connection.prepareStatement(Constants.DELETE_SUBJECT);		
			pstmtBook = connection.prepareStatement(Constants.DELETE_SUBBOOK);	

			pstmtBook.setLong(1, subjectId);			
			n = pstmtBook.executeUpdate();
			pstmtBook.close();

			pstmtSubject.setInt(1, subjectId);
			pstmtSubject.executeUpdate();
			pstmtSubject.close();
		}
		catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
			//throw new RuntimeException(e);

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		if(n==0) {
			System.out.println("No record to delete");
		}else {
			System.out.println("Subejct successfully deleted");
		}
	}
}
