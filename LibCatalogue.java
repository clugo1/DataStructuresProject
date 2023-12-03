import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class LibCatalogue {
    private List<String> bookDatabase;
    // using set data structure to check for duplicate titles when adding a book
    private Set<String> bookTitles;

    private Map<String, Queue<String>> waitingLists;

    public LibCatalogue() {
        bookDatabase = new ArrayList<>();
        bookTitles = new HashSet<>();
        waitingLists = new HashMap<>();
    }

    public boolean addBook(String bookName) {
        // checks if the adding book is an empty string
        if (bookName == null || bookName.trim().isEmpty()) {
            System.out.println("Sorry, But The Book Title Cannot Be Empty!");
            return false;
            // checks if we already have the book in the set (no duplicates in a set)
        } else if (bookTitles.contains(bookName)) {
            System.out.println("The Book '" + bookName + "' Already Exists in the Catalogue, Try Another Book!");
            return false;
        } else {
            bookDatabase.add(bookName);
            bookTitles.add(bookName);
            System.out.println("'" + bookName + "' has been added to the catalogue.");
            return true;
        }
    }

    public void deleteBook(String bookName) {
        if (bookTitles.contains(bookName)) {
            bookDatabase.remove(bookName);
            bookTitles.remove(bookName);
        }
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

    // this functions lets the user know that the next book in the waiting list is
    // available for checkout.
    public String notifyNextUser(String bookName) {
        Queue<String> waitingList = waitingLists.get(bookName);
        if (waitingList != null && !waitingList.isEmpty()) {
            // Removes the user from queue, returns their name
            return waitingList.poll();
        }
        // returning null means no one is waiting for the book
        return null;
    }

    // If a user no longer wants to wait for a book, we may remove them from the
    // waiting list
    public void removeFromWaitingList(String bookName, String userName) {
        Queue<String> waitingList = waitingLists.get(bookName);
        if (waitingList != null) {
            waitingList.remove(userName);
        }
    }
}
