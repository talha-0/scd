package items;
import abstractclasses.Item;

public class Newspaper extends Item {
    private String publisherCompany;
    private String publicationDate;

    public Newspaper(String t, String publisher, String date, int popularity) {
        super(t,0,popularity);
        publisherCompany = publisher;
        publicationDate = date;
    }
    @Override
    public double calculateCost() {
        return getCost() + 10.0 + 5.0; // Assuming fixed costs for newspapers
    }
    public void set_title(String t){
        super.set_title(t);
    }

    @Override
    public void displayInfo() {
        System.out.println("Type: Newspaper");
        super.displayInfo();
        System.out.println("Publisher: " + publisherCompany);
        System.out.println("Publication Date: " + publicationDate);
    }
}