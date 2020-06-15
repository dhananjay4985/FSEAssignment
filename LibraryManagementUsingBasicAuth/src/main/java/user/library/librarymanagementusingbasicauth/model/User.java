package user.library.librarymanagementusingbasicauth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import user.library.librarymanagementusingbasicauth.utility.Category;

@Entity
@Table(name="user")
public class User {

	@Id
	@Column(name = "userid")
	private Long userId;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "category")
	private Category userCategory;

	public User() {
		super();
	}
	
	public User(Long userId, String email, String password, Category userCategory) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.userCategory = userCategory;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Category getUserCategory() {
		return userCategory;
	}
	public void setUserCategory(Category userCategory) {
		this.userCategory = userCategory;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", userCategory="
				+ userCategory + "]";
	}
}
