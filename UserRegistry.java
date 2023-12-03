import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRegistry {
    private Map<String, User> users;

    public UserRegistry() {
        users = new HashMap<>();
    }

    public void addUser(String username) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username));
        }
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public Map<String, String> getBorrowingHistory(String username) {
        User user = users.get(username);
        if (user != null) {
            return user.getBorrowedBooks();
        }
        return new HashMap<>(); // Returns an empty map if user not found
    }

    public void borrowBook(String username, String bookName, String dueDate) {
        User user = users.get(username);
        if (user != null) {
            user.borrowBook(bookName, dueDate);
        }
    }

    // changed returnBook to boolean method so that we can implement an efficient
    // waitlist Queue structure.
    public boolean returnBook(String username, String bookName) {
        User user = users.get(username);
        if (user != null && user.getBorrowedBooks().containsKey(bookName)) {
            user.returnBook(bookName);
            return true; // True == book was returned correctly
        }
        return false; // Eror locating the user, or they dont have the book in the first place
    }
}
