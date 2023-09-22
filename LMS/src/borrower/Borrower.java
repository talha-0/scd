package borrower;
import java.util.ArrayList;
import java.util.List;

import abstractclasses.Item;

public class Borrower {
    private int id;
    private List<Item> borrowedItems;
    private List<Item> returnedItems;

    public Borrower(int id) {
        this.id = id;
        this.borrowedItems = new ArrayList<Item>();
        this.returnedItems = new ArrayList<Item>();
    }

    public int getId() {
        return id;
    }

    public List<Item> getBorrowedItems() {
        return borrowedItems;
    }
    
    public void borrowItem(Item item) {
        borrowedItems.add(item);
    }

    public boolean hasBorrowedItem(Item item) {
        return borrowedItems.contains(item);
    }

    public void returnItem(Item item) {
        returnedItems.add(item);
    }

    public boolean hasReturned(Item item){
        if(borrowedItems.contains(item) && !returnedItems.contains(item))
            return false;
        return true;
    }

    public void currentBorrowed(){
        System.out.println("Current borrowed items by borrower: "+id+" are:\n");
        for(Item item:borrowedItems){
            if(!hasReturned(item))
                item.displayInfo();
        }
    }
}