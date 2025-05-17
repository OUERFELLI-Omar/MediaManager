package models;

import interfaces.emprunterInterface;
import interfaces.reserverInterface;
import java.time.Year;

public class DVD extends Media implements emprunterInterface, reserverInterface {
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
        System.out.printf("DVD: %s (%d)%nRéalisateur: %s%nDurée: %d min%nStatut: %s%n",
                getTitle(),
                getPublicationYear().getValue(),
                director,
                duration,
                getStatus());
    }

    @Override
    public void borrow() {
        if (!isAvailable()) {
            System.out.println("Erreur: Le DVD est déjà emprunté.");
            return;
        }
        if (isReserved()) {
            System.out.println("Attention: Réservation active - emprunt prioritaire.");
            setReserved(false);
        }
        setAvailable(false);
        System.out.printf("DVD \"%s\" emprunté avec succès.%n", getTitle());
    }

    @Override
    public void returnItem() {
        setAvailable(true);
        System.out.printf("DVD \"%s\" retourné.%n", getTitle());
    }

    @Override
    public void reserve() {
        if (isReserved()) {
            System.out.println("Erreur: Ce DVD est déjà réservé.");
            return;
        }
        if (!isAvailable()) {
            System.out.println("Erreur: Le DVD est actuellement emprunté.");
            return;
        }
        setReserved(true);
        System.out.printf("Réservation confirmée pour le DVD: %s%n", getTitle());
    }

    @Override
    public void cancelReservation() {
        if (!isReserved()) {
            System.out.println("Aucune réservation active pour ce DVD.");
            return;
        }
        setReserved(false);
        System.out.printf("Réservation annulée pour le DVD: %s%n", getTitle());
    }

    // Méthode utilitaire pour la durée formatée
    public String getFormattedDuration() {
        return String.format("%dh%02d", duration / 60, duration % 60);
    }
}