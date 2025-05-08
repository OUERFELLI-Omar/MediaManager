package models;

import interfaces.emprunterInterface;
import java.time.Year;

public class DVD extends Media implements emprunterInterface {
    private String director;
    private int duration;

    public DVD() {}
    public DVD(int id, String title, Year publicationYear, String director, int duration) {
        super(id, title, publicationYear, true);
        this.director = director;
        this.duration = duration;
    }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    @Override
    public void displayInfo() {
        System.out.println("DVD: " + getTitle() + " directed by " + director + " (" + getPublicationYear() + ")");
    }

    // Méthodes de l'interface
    @Override
    public void borrow() {
        if (isAvailable()) {
            setAvailable(false);
            System.out.println("DVD " + getTitle() + " has been borrowed.");
        } else {
            System.out.println("DVD " + getTitle() + " is not available.");
        }
    }

    @Override
    public void returnItem() {
        setAvailable(true);
        System.out.println("DVD " + getTitle() + " has been returned.");
    }
}