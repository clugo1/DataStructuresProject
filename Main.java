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

        // strings that will change during the loop with switch cases to prevent
        // duplicate errors
        String choice;
        String username;
        String bookName;
        do {
            System.out.println(
                    "Would you like to 'borrow', 'return', 'add to waitlist', 'remove from waitlist', or 'exit'?");
            choice = scanner.nextLine();

            switch (choice.toLowerCase()) {
                // In the borrow case, we check if the book is available then enter a return
                // date
                case "borrow":
                    System.out.println("Enter username:");
                    username = scanner.nextLine();
                    System.out.println("Enter book name to borrow:");
                    bookName = scanner.nextLine();

                    if (catalogue.isBookAvailable(bookName)) {
                        System.out.println("Enter due date for the book (YYYY-MM-DD):");
                        String dueDate = scanner.nextLine();
                        userRegistry.borrowBook(username, bookName, dueDate);
                        System.out.println("Book borrowed successfully.");
                    } else {
                        // if its already borrowed, we add a request for the user to join waitlist or
                        // not
                        System.out.println(
                                "The book is currently borrowed. Would you like to be added to the waiting list? (yes/no)");
                        String response = scanner.nextLine();
                        if (response.equalsIgnoreCase("yes")) {
                            catalogue.addToWaitingList(bookName, username);
                            System.out.println("You have been added to the waiting list for " + bookName);
                        }
                    }
                    break;
                case "return":
                    System.out.println("Enter username:");
                    username = scanner.nextLine();
                    System.out.println("Enter book name to return:");
                    bookName = scanner.nextLine();
                    if (userRegistry.returnBook(username, bookName)) {
                        System.out.println("Book returned successfully.");
                        String nextUser = catalogue.notifyNextUser(bookName);
                        if (nextUser != null) {
                            System.out.println(nextUser + " has been notified that " + bookName + " is now available.");
                        }
                    } else {
                        System.out
                                .println("Could not return the book. Either it wasn't borrowed or an error occurred.");
                    }
                    break;
                // when the user is added, we ask the name of the book aswell. If its not in the
                // catalogue, they are denied
                case "add to waitlist":
                    System.out.println("Enter username:");
                    username = scanner.nextLine().trim();
                    System.out.println("Enter the name of the book to be added to the waitlist:");
                    bookName = scanner.nextLine().trim();
                    if (catalogue.isBookPresent(bookName)) { // Check if the book exists in the catalogue
                        catalogue.addToWaitingList(bookName, username);
                        System.out.println("You've been added to the waitlist for " + bookName);
                    } else {
                        System.out.println("The book '" + bookName + "' does not exist in the catalogue.");
                    }
                    break;
                // checks if they are in the waitlist, as well as if they book exists
                case "remove from waitlist":
                    System.out.println("Enter username:");
                    username = scanner.nextLine();
                    System.out.println("Enter the name of the book to remove from the waitlist:");
                    bookName = scanner.nextLine();

                    if (catalogue.isBookPresent(bookName) && catalogue.isBookOnWaitingList(bookName)) {
                        catalogue.removeFromWaitingList(bookName, username);
                        System.out.println("You've been removed from the waitlist for " + bookName);
                    } else {
                        // Informs the user it does not exist
                        if (!catalogue.isBookPresent(bookName)) {
                            System.out.println("The book '" + bookName + "' does not exist in the catalogue.");
                        } else {
                            System.out.println("There is no waitlist for '" + bookName + "', or you are not on it.");
                        }
                    }
                    break;
            }
            // exits the program
        } while (!choice.equalsIgnoreCase("exit"));

        // Closing the scanner before the program ends
        scanner.close();
    }
}
