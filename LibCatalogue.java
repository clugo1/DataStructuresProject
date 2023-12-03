import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class LibCatalogue {
    private List<String> bookDatabase;
    // using set data structure to check for duplicate titles when adding a book
    private Set<String> bookTitles;
    // using queue data structure to implement a waiting list for books that are
    // currently checked out
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

    // Adds a user to the waiting list for a book
    public void addToWaitingList(String bookName, String username) {
        waitingLists.computeIfAbsent(bookName, k -> new LinkedList<>()).offer(username);
    }

    // Notifies the next user on the waiting list for a book, in case they want to
    // check
    public String notifyNextUser(String bookName) {
        Queue<String> waitingQueue = waitingLists.get(bookName);
        if (waitingQueue != null && !waitingQueue.isEmpty()) {
            return waitingQueue.poll(); // Removes the user from the queue and returns their name
        }
        return null;
    }

    // Removes a user from the waiting list for a book' waiting list that they were
    // added to
    public void removeFromWaitingList(String bookName, String username) {
        Queue<String> waitingQueue = waitingLists.get(bookName);
        if (waitingQueue != null) {
            waitingQueue.remove(username);
        }
    }

    // Checks if a book is available for borrowing
    public boolean isBookAvailable(String bookName) {
        // first we must check if book is in waitinglist
        // if it is then we can continue
        return bookDatabase.contains(bookName) && !isBookOnWaitingList(bookName);
    }

    // method that helps to check if its in the database.
    public boolean isBookOnWaitingList(String bookName) {
        Queue<String> waitingQueue = waitingLists.get(bookName);
        return waitingQueue != null && !waitingQueue.isEmpty();
    }

}
