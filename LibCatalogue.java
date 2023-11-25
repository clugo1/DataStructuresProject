import java.util.ArrayList;
import java.util.List;

public class LibCatalogue {
    private List<String> bookDatabase;

    public LibCatalogue() {
        bookDatabase = new ArrayList<>();
    }

    public void addBook(String bookName) {
        bookDatabase.add(bookName);
    }

    public void deleteBook(String bookName) {
        bookDatabase.remove(bookName);
    }

    public void editBook(String oldName, String newName) {
        int bookIndex = bookDatabase.indexOf(oldName);
        if (bookIndex != -1) {
            bookDatabase.set(bookIndex, newName);
        }
    }

    public boolean isBookPresent(String bookName) {
        return bookDatabase.contains(bookName);
    }

    public List<String> getBookDatabase() {
        return bookDatabase;
    }
}
