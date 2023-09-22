package abstractclasses;

import interfaces.Configuration;

public abstract class Item implements Configuration {
    private static int nextId;
    private int id;
    private String title;
    private int popularityCount;
    private double cost;
    static {
        nextId = 1;
    }
    public Item(String title,double cost,int popularityCount) {
        this.id = nextId++;
        this.title = title;
        this.popularityCount = popularityCount;
        this.cost = cost;
    }
    public double getCost() {
        return cost;
    }
    public abstract double calculateCost();
    public int getPopularityCount() {
        return popularityCount;
    }

    public void incrementPopularity() {
        popularityCount++;
    }
    public int get_id() {
        return id;
    }
    public String get_title() {
        return title;
    }
    public void set_title(String t) {
        title=t;
    }
    @Override
    public void displayInfo() {
        System.out.println("ID: " + id + "\nTitle: " + title);
    }
}