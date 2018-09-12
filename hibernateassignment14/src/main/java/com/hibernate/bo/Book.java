package com.hibernate.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="book_id")
	private int bookId;
	
	@Column(name="title")	
	private String title;
	
	@Column(name="price")
	private double price;
	
	@Column(name="volume")
	private int volume;
	
	@Column(name="publishdate")
	private Date publishDate;
	
	public Book() {
		//default constructor
	}
	//getter & setter
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", price=" + price + ", volume=" + volume
				+ ", publishDate=" + publishDate + "]";
	}
}
