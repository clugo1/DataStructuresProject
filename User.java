import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private Map<String, String> borrowedBooks; // Maps book names to their due dates

    public User(String username) {
        this.username = username;
        this.borrowedBooks = new HashMap<>();
    }

    // Method to borrow a book with a due date
    public void borrowBook(String bookName, String dueDate) {
        borrowedBooks.put(bookName, dueDate);
    }
    public void returnBook(String bookName) {
        borrowedBooks.remove(bookName);
    }

    // Method to update the due date of a borrowed book
    public void updateDueDate(String bookName, String newDueDate) {
        if (borrowedBooks.containsKey(bookName)) {
            borrowedBooks.put(bookName, newDueDate);
        }
    }

    public Map<String, String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getUsername() {
        return username;
    }
}
