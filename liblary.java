import java.util.ArrayList;
import java.util.Scanner;

// Book class
class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("You have borrowed: " + title);
        } else {
            System.out.println("Sorry, this book is already borrowed.");
        }
    }

    public void returnBook() {
        isAvailable = true;
        System.out.println("You have returned: " + title);
    }
}

// Library class
class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        System.out.println("\nLibrary Books:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + 
                               " - " + (book.isAvailable() ? "Available" : "Not Available"));
        }
    }

    public Book searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

// Main class
public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Adding sample books
        library.addBook(new Book("Harry Potter", "J.K. Rowling"));
        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien"));

        while (true) {
            System.out.println("\n1. View Books\n2. Borrow Book\n3. Return Book\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                library.displayBooks();
            } else if (choice == 2) {
                System.out.print("Enter book title to borrow: ");
                String title = scanner.nextLine();
                Book book = library.searchBook(title);
                if (book != null) {
                    book.borrowBook();
                } else {
                    System.out.println("Book not found.");
                }
            } else if (choice == 3) {
                System.out.print("Enter book title to return: ");
                String title = scanner.nextLine();
                Book book = library.searchBook(title);
                if (book != null) {
                    book.returnBook();
                } else {
                    System.out.println("Book not found.");
                }
            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}
