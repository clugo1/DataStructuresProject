import java.util.Map;
import java.util.Scanner;

public class Main {
    // Method to show the welcome message
    private static void showWelcomeMessage() {
        System.out.println("Welcome to the FIU Library Management System!");
        System.out.println("Please follow the prompts to add books and users.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Call the method to show the welcome message
        showWelcomeMessage();

        // Initialize the library catalogue and book manager
        LibCatalogue catalogue = new LibCatalogue();
        Book bookManager = new Book(catalogue);

        // Initialize the user registry
        UserRegistry userRegistry = new UserRegistry();

        // Adding multiple books
        String input;
        do {
            System.out.println("Enter the name of a book to add to the catalogue (or type 'done' to finish):");
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("done")) {
                bookManager.addBookToCatalogue(input);
                System.out.println("'" + input + "' has been added to the catalogue.");
            }
        } while (!input.equalsIgnoreCase("done"));

        // Adding multiple users
        do {
            System.out.println("Enter a username to register (or type 'done' to finish):");
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("done")) {
                userRegistry.addUser(input);
                System.out.println("User '" + input + "' has been registered.");
            }
        } while (!input.equalsIgnoreCase("done"));

        // Displaying the added books and users for confirmation
        System.out.println("\nCurrent Books in Catalogue:");
        for (String book : catalogue.getBookDatabase()) {
            System.out.println(book);
        }

        System.out.println("\nRegistered Users:");
        for (User user : userRegistry.getAllUsers()) {
            System.out.println(user.getUsername());
        }
        String choice;
        do {
            System.out.println("Would you like to 'borrow', 'return', or 'exit'?");
            choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("borrow")) {
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter book name to borrow:");
                String bookName = scanner.nextLine();
                System.out.println("Enter due date for the book (YYYY-MM-DD):");
                String dueDate = scanner.nextLine();

                userRegistry.borrowBook(username, bookName, dueDate);
                System.out.println("Book borrowed successfully.");
            } else if (choice.equalsIgnoreCase("return")) {
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter book name to return:");
                String bookName = scanner.nextLine();

                userRegistry.returnBook(username, bookName);
                System.out.println("Book returned successfully.");
            }
        } while (!choice.equalsIgnoreCase("exit"));


        // Example code to print borrowing history in the Main class
        System.out.println("Enter username to check borrowing history:");
        String usernameToCheck = scanner.nextLine();
        Map<String, String> borrowingHistory = userRegistry.getBorrowingHistory(usernameToCheck);
        if (borrowingHistory.isEmpty()) {
            System.out.println(usernameToCheck + " has no borrowed books.");
        } else {
            System.out.println(usernameToCheck + "'s Borrowing History:");
            for (Map.Entry<String, String> entry : borrowingHistory.entrySet()) {
                System.out.println("Book: " + entry.getKey() + ", Due Date: " + entry.getValue());
            }
        }


        scanner.close();
    }
}
