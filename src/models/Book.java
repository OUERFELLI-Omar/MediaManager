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
        System.out.printf("Livre: %s par %s (%d)%nISBN: %s%nStatut: %s%n",
                getTitle(), author, getPublicationYear().getValue(),
                isbn, getStatus());
    }

    @Override
    public void borrow() {
        if (!isAvailable()) {
            System.out.println("Erreur: Le livre est déjà emprunté.");
            return;
        }
        if (isReserved()) {
            System.out.println("Erreur: Le livre est réservé pour un autre utilisateur.");
            return;
        }
        setAvailable(false);
        System.out.printf("Emprunt confirmé : %s%n", getTitle());
    }

    @Override
    public void returnItem() {
        setAvailable(true);
        setReserved(false);
        System.out.printf("Livre retourné : %s%n", getTitle());
    }

    @Override
    public void reserve() {
        if (isReserved()) {
            System.out.println("Erreur: Réservation déjà active.");
            return;
        }
        if (!isAvailable()) {
            System.out.println("Erreur: Le livre est actuellement emprunté.");
            return;
        }
        setReserved(true);
        System.out.printf("Réservation confirmée pour : %s%n", getTitle());
    }

    @Override
    public void cancelReservation() {
        if (!isReserved()) {
            System.out.println("Aucune réservation active.");
            return;
        }
        setReserved(false);
        System.out.printf("Réservation annulée pour : %s%n", getTitle());
    }
}