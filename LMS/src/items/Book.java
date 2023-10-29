package items;
import abstractclasses.Item;

public class Book extends Item {
    private static final double GST = 200.0;
    private String author;
    private int year;

    public Book(String title, String author, int year, double cost, int popularityCount) {
        super(title,cost,popularityCount);
        this.author = author;
        this.year = year;
    }
    public String get_author() {
        return author;
    }
    public int get_year() {
        return year;
    }
    @Override
    public double calculateCost() {
        return getCost() + (0.2 * getCost()) + GST;
    }
    public void set_title(String t){
        super.set_title(t);
    }
    @Override
    public void displayInfo() {
        System.out.println("Type: Book");
        super.displayInfo();
        System.out.println(" By " + author + " (" + year + ")");
    }
}

