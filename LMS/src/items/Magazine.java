package items;
import java.util.ArrayList;

import abstractclasses.Item;

public class Magazine extends Item {
    private ArrayList<String> authors;
    private String publisherCompany;

    public Magazine(String t, ArrayList<String> authors, String publisher,int popularity, double cost) {
        super(t,cost,popularity);
        this.authors = authors;
        publisherCompany = publisher;
    }
    public String get_author() {
        return publisherCompany;
    }
    public int get_year() {
        return 0;
    }
    @Override
    public double calculateCost() {
        return getCost() * getPopularityCount();
    }
    public void set_title(String t){
        super.set_title(t);
    }
    @Override
    public void displayInfo() {
        System.out.println("Type: Magazine");
        super.displayInfo();
        System.out.println("Authors: " + authors);
        System.out.println("Publisher: " + publisherCompany);
    }
}