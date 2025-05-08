package models;

import java.time.Year;

public abstract class Media {
    private int id;
    private String title;
    private Year publicationYear;
    private boolean isAvailable;

    public Media() {}

    public Media(int id, String title, Year publicationYear, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.isAvailable = isAvailable;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Year getPublicationYear() { return publicationYear; }
    public void setPublicationYear(Year publicationYear) { this.publicationYear = publicationYear; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public abstract void displayInfo();
}