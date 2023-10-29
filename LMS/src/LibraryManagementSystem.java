import java.util.ArrayList;
import java.util.Scanner;
import abstractclasses.Item;
import items.*;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        library.loadItemsFromFile("items.txt");
        while (true) {
            System.out.println("Library Management System Menu:");
            System.out.println("1. Hot Picks!");
            System.out.println("2. Borrow an item");
            System.out.println("3. Add Item");
            System.out.println("4. Edit Item");
            System.out.println("5. Delete Item");
            System.out.println("6. View All Items");
            System.out.println("7. View Item by ID");
            System.out.println("8. View Borrowers List");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    library.displayHotPicks();
                    break;
                case 2:
                    System.out.print("Enter the ID of the item to borrow: ");
                    int borrowItemId = scanner.nextInt();
                    System.out.print("Enter your Borrower ID: ");
                    int borrowerId = scanner.nextInt();
                    library.borrowItem(borrowItemId, borrowerId);
                    break;
                case 3:
                    System.out.println("Enter the type of item to add:");
                    System.out.println("1. Book");
                    System.out.println("2. Magazine");
                    System.out.println("3. Newspaper");
                    System.out.print("Enter your choice: ");
                    int itemType = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter the title of the item: ");
                    String title = scanner.nextLine();
                    switch (itemType) {
                        case 1:
                            System.out.print("Enter the author of the book: ");
                            String author = scanner.nextLine();
                            System.out.print("Enter the year of publication: ");
                            int year = scanner.nextInt();
                            System.out.print("Enter the popularity count: ");
                            int popularity = scanner.nextInt();
                            System.out.print("Enter the price of the book: ");
                            int price = scanner.nextInt();
                            Book book = new Book(title, author, year, price, popularity);
                            library.addItem(book);
                            break;
                        case 2:
                            ArrayList<String> authors = new ArrayList<>();
                            System.out.print("Enter the number of authors: ");
                            int authorCount = scanner.nextInt();
                            scanner.nextLine(); 
                            for (int i = 0; i < authorCount; i++) {
                                System.out.print("Enter the name of author " + (i+1) + ": ");
                                authors.add(scanner.nextLine());
                            }
                            System.out.print("Enter the publisher of the magazine: ");
                            String publisher = scanner.nextLine();
                            System.out.print("Enter the popularity count: ");
                            popularity = scanner.nextInt();
                            System.out.print("Enter the price of the magazine: ");
                            price = scanner.nextInt();
                            Magazine magazine = new Magazine(title, authors, publisher, price, popularity);
                            library.addItem(magazine);
                            break;
                        case 3:
                            System.out.print("Enter the publisher of the newspaper: ");
                            publisher = scanner.nextLine();
                            System.out.print("Enter the popularity count: ");
                            popularity = scanner.nextInt();
                            scanner.nextLine(); 
                            System.out.print("Enter the publication date of the newspaper: ");
                            String date = scanner.nextLine();
                            Newspaper newspaper = new Newspaper(title, publisher, date, popularity);
                            library.addItem(newspaper);
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                            break;
                    }
                    break;
                case 4:
                    System.out.print("Enter the ID of the item to edit: ");
                    int editItemId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the new title of the item: ");
                    String newTitle = scanner.nextLine();
                    library.editItem(editItemId, newTitle);
                    break;
                case 5:
                    System.out.print("Enter the ID of the item to delete: ");
                    int deleteItemId = scanner.nextInt();
                    scanner.nextLine();
                    library.deleteItem(deleteItemId);
                    break;
                case 6:
                    library.displayAllItems();
                    break;
                case 7:
                    System.out.print("Enter the ID of the item to view: ");
                    int viewItemId = scanner.nextInt();
                    scanner.nextLine();
                    Item itemToView = library.getItemById(viewItemId);
                    if (itemToView != null) {
                        library.displayItemDetails(itemToView);
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;
                case 8:
                    library.displayBorrowersAndBorrowedItems();
                    break;
                case 9:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
}
