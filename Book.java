public class Book {
    private LibCatalogue catalogue;

    public Book(LibCatalogue catalogue) {
        this.catalogue = catalogue;
    }

    public void addBookToCatalogue(String bookName) {
        catalogue.addBook(bookName);
    }

    public void editBookInCatalogue(String oldName, String newName) {
        catalogue.editBook(oldName, newName);
    }

    public void deleteBookFromCatalogue(String bookName) {
        catalogue.deleteBook(bookName);
    }

    public boolean checkIfBookExists(String bookName) {
        return catalogue.isBookPresent(bookName);
    }
}
