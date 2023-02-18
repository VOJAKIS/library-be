package sk.umb.example.library.book;

public class Book {
	protected long id;
	protected String name;
	protected String isbn;
	protected String authorFirstName;
	protected String authorLastName;
	protected Integer bookCount;

	public Book(long id, String name, String isbn, String authorFirstName, String authorLastName, Integer bookCount) {
		this.id = id;
		this.name = name;
		this.isbn = isbn;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.bookCount = bookCount;
	}
}
