import java.io.*;
import java.util.*;
import abstractclasses.Item;
import borrower.Borrower;
import items.*;
public class Library {
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Borrower> borrowers = new ArrayList<>();
    public boolean isAvailable(Item item){
        for(Borrower borrower:borrowers){
            if(borrower.hasReturned(item)==false)
                return false;
        }
        return true;
    }
    
    public void borrowItem(int itemId, int borrowerId) {
        Item item = getItemById(itemId);
        Borrower borrower = getBorrowerById(borrowerId);
        if(borrower==null){
            borrower = new Borrower(borrowerId);
            borrowers.add(borrower);
        }
        if (item != null) {
            if (isAvailable(item) && !borrower.hasBorrowedItem(item)) {
                item.incrementPopularity();
                borrower.borrowItem(item);
                System.out.println("Item borrowed successfully.\nBill: "+item.calculateCost());
            } else {
                System.out.println("Item is not available for borrowing or already borrowed.");
            }
        } else {
            System.out.println("Item or borrower not found.");
        }
    }
    private Borrower getBorrowerById(int borrowerId) {
        for (Borrower borrower : borrowers) {
            if (borrower.getId() == borrowerId) {
                return borrower;
            }
        }
        return null;
    }

    public void returnItem(int itemId, int borrowerId) {
        Item item = getItemById(itemId);
        Borrower borrower = getBorrowerById(borrowerId);

        if (item != null && borrower != null) {
            if(borrower.hasBorrowedItem(item)){
                if (borrower.hasReturned(item)){
                    System.out.println("Item was returned previously.");
                }
                else{
                    borrower.returnItem(item);
                    System.out.println("Item returned successfully.");
                }
            }else{
                System.out.println("The borrower didn't borrow this item.");
            }
        } else {
            System.out.println("Item or borrower not found");
        }
    }
    public void displayBorrowersAndBorrowedItems() {
        System.out.println("Borrowers and Borrowed Items:");
        for (Borrower borrower : borrowers) {
            borrower.currentBorrowed();
        }
    }
    
    public void displayHotPicks() {
        items.sort((item1, item2) -> Integer.compare(item2.getPopularityCount(), item1.getPopularityCount()));

        System.out.println("Hot Picks - Items Sorted by Popularity:");
        for (Item item : items) {
            System.out.println("Title: " + item.get_title() + " - Popularity Count: " + item.getPopularityCount());
        }
    }
    
    public void addItem(Item item) {
        items.add(item);
    }

    public void editItem(int id, String title) {
        Item item = getItemById(id);
        if (item == null) {
            System.out.println("Item with ID " + id + " not found.");
        } else {
            item.set_title(title);
            System.out.println("Item with ID " + id + " edited successfully.");
        }
    }

    public void deleteItem(int id) {
        Item item = getItemById(id);
        if (item == null) {
            System.out.println("Item with ID " + id + " not found.");
        } else {
            items.remove(item);
            System.out.println("Item with ID " + id + " deleted successfully.");
        }
    }

    public void displayAllItems() {
        for (Item item : items) {
            item.displayInfo();
            System.out.println();
        }
    }
    
    public void displayItemDetails(Item item) {
        if (item != null) {
            item.displayInfo();
        } else {
            System.out.println("Item not found.");
        }
    }
    
    public Item getItemById(int id) {
        for (Item item : items) {
            if (item.get_id() == id) {
                return item;
            }
        }
        return null;
    }

    public void loadItemsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    int type = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    
                    switch (type) {
                        case 1: // Book
                            if (parts.length == 6) {
                                String author = parts[2];
                                int year = Integer.parseInt(parts[3].strip());
                                int popularity = Integer.parseInt(parts[4].strip());
                                int price = Integer.parseInt(parts[5].strip());
                                Book book = new Book(title, author, year, price, popularity);
                                items.add(book);
                            }
                            break;
                        case 2: // Magazine
                            if (parts.length >= 6) {
                                ArrayList<String> authors = new ArrayList<>();
                                String publisher = parts[parts.length-3];
                                int popularity = Integer.parseInt(parts[parts.length-2].strip());
                                int price = Integer.parseInt(parts[parts.length-1].strip());
                                for (int i = 2; i < parts.length-3; i++) {
                                    authors.add(parts[i]);
                                }
                                Magazine magazine = new Magazine(title, authors, publisher, popularity,price);
                                items.add(magazine);
                            }
                            break;
                        case 3: // Newspaper
                            if (parts.length == 5) {
                                String publisher = parts[2];
                                int popularity = Integer.parseInt(parts[3].strip());
                                String date = parts[4];
                                Newspaper newspaper = new Newspaper(title, publisher, date, popularity);
                                items.add(newspaper);
                            }
                            break;
                        default:
                            System.out.println("Invalid type: " + type);
                    }
                }else{
                    System.out.println("Line skipped because of incomplete info.\nInfo required>=5\nReceived:" + parts.length);
                }
            }
            System.out.println("Items loaded successfully from " + fileName);
        } catch (IOException e) {
            System.out.println("Error loading items from " + fileName);
        }
    }
}
