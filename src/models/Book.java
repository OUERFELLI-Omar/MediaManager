package models;

import interfaces.emprunterInterface;
import interfaces.reserverInterface;
import java.time.Year;

public class Book extends Media implements emprunterInterface, reserverInterface {
    private String author;
    private int pageCount;
    private String isbn;

    public Book() {}

    public Book(int id, String title, Year publicationYear, String author, int pageCount, String isbn) {
        super(id, title, publicationYear, true);
        this.author = author;
        this.pageCount = pageCount;
        this.isbn = isbn;
    }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getPageCount() { return pageCount; }
    public void setPageCount(int pageCount) { this.pageCount = pageCount; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    @Override
    public void displayInfo() {
        System.out.println("Book: " + getTitle() + " by " + author + " (" + getPublicationYear() + ")");
    }
    @Override
    public void borrow() {
        if (isAvailable()) {
            setAvailable(false);
            System.out.println("Book " + getTitle() + " has been borrowed.");
        } else {
            System.out.println("Book " + getTitle() + " is not available.");
        }
    }

    @Override
    public void returnItem() {
        setAvailable(true);
        System.out.println("Book " + getTitle() + " has been returned.");
    }

    @Override
    public void reserve() {
        System.out.println("Book " + getTitle() + " has been reserved.");
    }

    @Override
    public void cancelReservation() {
        System.out.println("Reservation for book " + getTitle() + " has been canceled.");
    }
}