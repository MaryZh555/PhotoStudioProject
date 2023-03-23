package models;

/**
 * Created by zhmas on 18.03.2023.
 */
public class Locations {
    private String name;
    private int cost;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int rentingCost) {
        this.cost = rentingCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    Locations(String name, int cost, String description) {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    static Locations[] locations;

    public static void createLocations() {
        locations = new Locations[]{
                new Locations("ChromaCharm Room", 10, "A cozy and intimate space with chromakey and all necessary attributes for portrait photography."),
                new Locations("Group Hub", 25, "A spacious room perfect for group and team photo shoots with a variety of backdrops and props available."),
                new Locations("River View Terrace", 30, "An outdoor location featuring a picturesque view of a small bridge over a nearby river, perfect for natural and scenic photography."),
        };
    }

    public static Locations[] getLocations() {
        return locations;
    }

}