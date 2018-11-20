package user.library.librarymanagementusingbasicauth.utility;

public enum Category {
	
	ADMIN("ADMIN"),MEMBER("MEMBER"),LIBRARIAN("LIBRARIAN"),PRINCIPAL("PRINCIPAL");
	
	private String role;
	 
	Category(String role) {
        this.role = role;
    }
 
    public String getRole() {
        return role;
    }
}
