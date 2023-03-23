package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhmas on 18.03.2023.
 */
public class Photographer {
    private String name;
    private int yearsOfExperience;
    private int cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int years) {
        this.yearsOfExperience = years;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    Photographer(String name, int years, int cost) {
        this.name = name;
        this.yearsOfExperience = years;
        this.cost = cost;
    }

    public static void createPhotographers() {
        Photographer[] photographers = {
                new Photographer("Tasha", 1, 10),
                new Photographer("Sasha", 2, 12),
                new Photographer("Misha", 3, 15),
                new Photographer("Dasha", 4, 18),
                new Photographer("Masha", 5, 25),
                new Photographer("Dimas", 6, 35),
        };

        for (Photographer photographer : photographers) {
            if (photographer.getYearsOfExperience() <= 2) {
                novice.add(photographer);
            } else if (photographer.getYearsOfExperience() > 2 && photographer.getYearsOfExperience() < 5) {
                experienced.add(photographer);
            } else {
                expert.add(photographer);
            }
        }
    }

    // Categorize
    static List<Photographer> novice = new ArrayList<>();
    static List<Photographer> experienced = new ArrayList<>();
    static List<Photographer> expert = new ArrayList<>();

    public static List<Photographer> getNoviceList() {
        return novice;
    }

    public static List<Photographer> getExperiencedList() {
        return experienced;
    }

    public static List<Photographer> getExpertsList() {
        return expert;
    }

}
