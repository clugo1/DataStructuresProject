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
        return new HashMap<>(); // Return empty map if user not found
    }


    public void borrowBook(String username, String bookName, String dueDate) {
        User user = users.get(username);
        if (user != null) {
            user.borrowBook(bookName, dueDate);
        }
    }
    public void returnBook(String username, String bookName) {
        User user = users.get(username);
        if (user != null) {
            user.returnBook(bookName);
        }
    }
}
// Example method to simulate book borrowing

